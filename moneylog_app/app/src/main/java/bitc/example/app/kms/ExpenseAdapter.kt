package bitc.example.app.kms

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bitc.example.app.databinding.ExpenseItemRecyclerViewBinding
import bitc.example.app.databinding.IncomItemRecyclerViewBinding
import bitc.example.app.dto.ExpenseLogDTO
import bitc.example.app.dto.IncomeLogDTO
import bitc.example.app.dto.TodoListDTO
import bitc.example.app.sagmin.DetailOutcomeActivity

class ExpenseAdapter(val datas: MutableList<TodoListDTO>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ExpenseViewHolder(ExpenseItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, index: Int) {

        val binding = (holder as ExpenseViewHolder).binding

//        binding.expenseItemDateData.text = datas[index].expenseDate
        binding.expenseItemCateData.text = datas[index].todoTitle
//        binding.expenseItemMoneyData.text = datas[index].expenseMoney
        binding.expenseItemMemoData.text = datas[index].todoMemo
        binding.expenseItemOptionData.text = datas[index].todoStatus
//        binding.expenseItemUseData.text = datas[index].expenseUse
        binding.expenseItemSeqData.text = datas[index].todoSeq.toString()

        binding.linearOutcome.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailOutcomeActivity::class.java).apply{
                putExtra("expenseLogSeq",datas[index].todoSeq)
                putExtra("expenseCate",datas[index].todoTitle)
//                putExtra("expenseMoney",datas[index].expenseMoney)
                putExtra("expenseMemo",datas[index].todoMemo)
                putExtra("paymentOption",datas[index].todoStatus)
//                putExtra("expenseUse",datas[index].expenseUse)
//                putExtra("expenseDate",datas[index].expenseDate)
            }
            context.startActivity(intent)
        }




    }

}