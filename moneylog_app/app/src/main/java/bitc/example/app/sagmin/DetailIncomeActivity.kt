package bitc.example.app.sagmin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import bitc.example.app.AppServerClass
import bitc.example.app.R
import bitc.example.app.databinding.ActivityDetailIncomeBinding
import bitc.example.app.dto.TodoListDTO
import bitc.example.app.kms.MonthlyListActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailIncomeActivity : AppCompatActivity() {

    private val binding: ActivityDetailIncomeBinding by lazy {
        ActivityDetailIncomeBinding.inflate(layoutInflater)
    }

    //  카테고리 선택
    private var selectedCategories : String? = null // 선택한 항목 저장

    //  자산방식 선택
    private var selectedBanks : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnBack.setOnClickListener{
            finish()
        }



//        어댑터에서 가져온 데이터를 변수를 선언해서 담아줌
        val todoSeq = intent.getIntExtra("todoSeq", -1)
        val todoTitle = intent.getStringExtra("todoTitle")
        val todoMemo = intent.getStringExtra("todoMemo")
        val todoStatus = intent.getStringExtra("todoStatus")

//        incomAdapter에서 값을 받아와서 상세페이지에 바인딩
        binding.todoSeq.text = todoSeq.toString()
        binding.todo.setText(todoTitle)
        binding.todoTitle.setText(todoTitle)
        binding.todoMemo.setText(todoMemo)
//        binding.todoStatus.setText(todoStatus)


//      수정 버튼 클릭시 발생할 이벤트
        binding.btnUpdate.setOnClickListener {
//            바인딩에 저장된 값을 변수에 담아서
            val seq = binding.todoSeq.text.toString().toInt()
            val todoTitle = binding.todoTitle.text.toString()
            val todoMemo = binding.todoMemo.text.toString()
//            val todoStatus = binding.todoStatus.text.toString()

//            매개변수로 보낼 DTO타입의 변수를 선언하고 클릭된 요소의 내용들을 그 안에 담아서 서버에 보냄
            var todo = TodoListDTO()
            todo.todoSeq = seq
            todo.todoMemo = todoMemo
            todo.todoTitle = todoTitle
            todo.todoStatus = todoStatus

            val api = AppServerClass.instance
            val call = api.updateTodo(todo)
            retrofitResponse(call)
        }


//      삭제 버튼 클릭시 발생할 이벤트
        binding.btnDrop.setOnClickListener {
//            val seq = binding.todoSeq.text?.toString()?.toIntOrNull() ?: -1

            val seq = binding.todoSeq.text.toString().toInt()


            if (seq == -1) {
                Log.e("DetailIncomeActivity", "삭제할 수 없는 데이터입니다.")
                return@setOnClickListener
            }

            // 삭제 확인 다이얼로그
            AlertDialog.Builder(this)
                .setTitle("삭제 확인")
                .setMessage("정말로 삭제하시겠습니까?")
                .setPositiveButton("삭제") { _, _ ->
                    val api = AppServerClass.instance
                    val call = api.deleteTodo(seq)
                    retrofitResponse(call) // 재사용 함수 호출
                }
                .setNegativeButton("취소", null)
                .show()
        }

    }





    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressedDispatcher.onBackPressed()
        return true
    }


    // Retrofit 통신 응답 부분
    // Callback<String> 부분이 서버에서 전달받을 데이터 타입임
    private fun retrofitResponse(call: Call<Int>) {
        call.enqueue(object : Callback<Int> {
            override fun onResponse(p0: Call<Int>, res: Response<Int>) {
                if (res.isSuccessful) {
                    // 서버에서 전달받은 데이터만 변수로 저장
                    val result = res.body()
                    Log.d("fullstack503", "result : $result")

                    val intent = Intent(this@DetailIncomeActivity, MonthlyListActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish() // 현재 액티비티 종료
                }
                else {
                    Log.d("fullstack503", "송신 실패")
                }
            }

            override fun onFailure(p0: Call<Int>, t: Throwable) {
                Log.d("fullstack503", "message : $t.message")
            }
        })
    }
}