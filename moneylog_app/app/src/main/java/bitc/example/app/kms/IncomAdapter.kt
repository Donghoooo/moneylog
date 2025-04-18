package bitc.example.app.kms

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bitc.example.app.databinding.IncomItemRecyclerViewBinding
import bitc.example.app.dto.IncomeLogDTO
import bitc.example.app.dto.TodoListDTO
import bitc.example.app.sagmin.DetailIncomeActivity

class IncomAdapter(val datas: MutableList<TodoListDTO>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return IncomViewHolder(IncomItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, index: Int) {

        val binding = (holder as IncomViewHolder).binding

//        리스트 바인딩 부분
        binding.incomItemSeqData.text = datas[index].todoSeq.toString()
        binding.incomItemCateData.text = datas[index].todoTitle
        binding.incomItemMemoData.text = datas[index].todoMemo
        binding.incomItemSourceData.text = datas[index].todoStatus

//        상세페이지 액티브로 데이터 보내기
        binding.linearIncome.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailIncomeActivity::class.java).apply {

//                "" 안에 명이 Detail 액티브에서 사용할 네임
                putExtra("todoSeq", datas[index].todoSeq)
                putExtra("todoTitle", datas[index].todoTitle)
                putExtra("todoMemo", datas[index].todoMemo)
                putExtra("todoStatus", datas[index].todoStatus)

            }
            context.startActivity(intent)
        }

//        binding.incomItemDateData.text = datas[index].incomeLogSeq
//        binding.incomItemCateData.text = datas[index].incomeCate
//        binding.incomItemMoneyData.text = datas[index].incomeMoney
//        binding.incomItemMemoData.text = datas[index].incomeMemo
//        binding.incomItemSourceData.text = datas[index].incomeSource
//        binding.incomItemUseData.text = datas[index].incomeUse
//        binding.incomItemSeqData.text = datas[index].incomeLogSeq.toString()
//
//        binding.linearIncome.setOnClickListener {
//            val context = holder.itemView.context
//            val intent = Intent(context, DetailIncomeActivity::class.java).apply {
//                putExtra("incomeLogSeq", datas[index].incomeLogSeq)
//                putExtra("incomeDate", datas[index].incomeDate)
//                putExtra("incomeCate", datas[index].incomeCate)
//                putExtra("incomeMoney", datas[index].incomeMoney)
//                putExtra("incomeSource", datas[index].incomeSource)
//                putExtra("incomeMemo", datas[index].incomeMemo)
//            }
//            context.startActivity(intent)
//        }

    }




}