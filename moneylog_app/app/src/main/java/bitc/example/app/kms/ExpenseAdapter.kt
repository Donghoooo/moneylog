package bitc.example.app.kms

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bitc.example.app.AppServerClass
import bitc.example.app.databinding.ExpenseItemRecyclerViewBinding
import bitc.example.app.databinding.IncomItemRecyclerViewBinding
import bitc.example.app.dto.ExpenseLogDTO
import bitc.example.app.dto.IncomeLogDTO
import bitc.example.app.dto.TodoListDTO
import bitc.example.app.sagmin.DetailIncomeActivity
import bitc.example.app.sagmin.DetailOutcomeActivity
import retrofit2.Callback

class ExpenseAdapter(
    val datas: MutableList<TodoListDTO>,
    private val onStatusChanged: () -> Unit // ✅ 콜백 추가
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ExpenseViewHolder(ExpenseItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, @SuppressLint("RecyclerView") index: Int) {

        val binding = (holder as ExpenseViewHolder).binding
        val item = datas[index]
        val done: String = "done"

//        리스트 바인딩 부분
        binding.expenseItemSeqData.text = datas[index].todoSeq.toString()
        binding.expenseItemCateData.text = datas[index].todoTitle
        binding.expenseItemMemoData.text = datas[index].todoMemo
        binding.expenseItemSourceData.text = datas[index].todoStatus


        // 체크박스 클릭 리스너
        binding.todoCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val todoSeq = item.todoSeq

                val todo = TodoListDTO().apply {
                    this.todoSeq = todoSeq
                    this.todoStatus = done
                }

                val api = AppServerClass.instance
                val call = api.updateStatus(todo)

                call.enqueue(object : Callback<Int> {
                    override fun onResponse(p0: retrofit2.Call<Int>, res: retrofit2.Response<Int>) {
                        if (res.isSuccessful) {
                            val result = res.body()
                            Log.d("csy", "status 변경 성공: $result")

                            // ✅ 리스트에서 항목 제거
                            datas.removeAt(index)
                            notifyItemRemoved(index)
                            notifyItemRangeChanged(index, datas.size)

                            // ✅ 콜백으로 프래그먼트에게 알림
                            onStatusChanged()
                        } else {
                            Log.d("csy", "상태 변경 실패")
                        }
                    }

                    override fun onFailure(p0: retrofit2.Call<Int>, t: Throwable) {
                        Log.d("csy", "서버 에러: ${t.message}")
                    }
                })
            }
        }



//        상세페이지 액티브로 데이터 보내기
        binding.linearExpense.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailOutcomeActivity::class.java).apply {

//                "" 안에 명이 Detail 액티브에서 사용할 네임
                putExtra("todoSeq", datas[index].todoSeq)
                putExtra("todoTitle", datas[index].todoTitle)
                putExtra("todoMemo", datas[index].todoMemo)
                putExtra("todoStatus", datas[index].todoStatus)

            }
            context.startActivity(intent)
        }

    }




}