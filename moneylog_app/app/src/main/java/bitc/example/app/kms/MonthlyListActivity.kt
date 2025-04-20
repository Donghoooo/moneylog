package bitc.example.app.kms

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import bitc.example.app.AppServerClass
import bitc.example.app.R
import bitc.example.app.databinding.ActivityMonthlyListBinding
import bitc.example.app.dto.TodoListDTO
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonthlyListActivity : AppCompatActivity() {

    // 👇 여기 선언 추가!
    private lateinit var fragmentIncom: FragmentIncom
    private lateinit var fragmentExpense: FragmentExpense

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMonthlyListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        add 버튼 클릭 시 todo 추가
        binding.addTodo.setOnClickListener {

//            입력된 값을 담는 변수 editTodo
            val editTodo = binding.editTodo.text.toString()

//          통신을 보낼 TodoListDTO 타입의 매개변수 todo
            var todo = TodoListDTO()
            todo.todoTitle = editTodo
            val api = AppServerClass.instance
            val call = api.postSignUp(todo)
            signUpProcess(call)

            binding.editTodo.setText("")
        }

        // 수입/지출 버튼 토글
        val toggleGroup = findViewById<RadioGroup>(R.id.tabRadioGroup)
        val btnIncome = findViewById<RadioButton>(R.id.btn_income)
        val btnExpense = findViewById<RadioButton>(R.id.btn_expense)

        // 초기값 설정
        btnIncome.isChecked = true

        // 선택 변경 리스너
        toggleGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btn_income -> {
                    // "수입" 선택 시
                }
                R.id.btn_expense -> {
                    // "지출" 선택 시
                }
            }
        }









        val fragmentManager: FragmentManager = supportFragmentManager
        var transaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentIncom = FragmentIncom()
        fragmentExpense = FragmentExpense()

        transaction.add(R.id.layout_fragment_base, fragmentIncom)
        transaction.commit()

        binding.btnIncome.setOnClickListener {
            transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment_base, fragmentIncom)
            transaction.setReorderingAllowed(true)
            transaction.addToBackStack("")
            transaction.commit()
        }

        binding.btnExpense.setOnClickListener {
            transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.layout_fragment_base, fragmentExpense)
            transaction.setReorderingAllowed(true)
            transaction.addToBackStack("")
            transaction.commit()
        }
    }

    // Retrofit 통신 응답 부분
    // Callback<String> 부분이 서버에서 전달받을 데이터 타입임
    private fun signUpProcess(call: Call<Int>) {
        call.enqueue(object : Callback<Int> {
            override fun onResponse(p0: Call<Int>, res: Response<Int>) {
                if (res.isSuccessful) {
                    Log.d("fullstack503", "result : ${res.body()}")
                    // 👉 투두 추가 성공 시 리스트 새로고침
                    fragmentIncom.refreshTodoList()
                } else {
                    Log.d("fullstack503", "송신 실패")
                }
            }

            override fun onFailure(p0: Call<Int>, t: Throwable) {
                Log.d("fullstack503", "message : $t")
            }
        })
    }


}