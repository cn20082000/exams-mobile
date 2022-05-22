package com.cn.exams.ui.bank.questionedit.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.cn.exams.core.BaseRecyclerAdapter
import com.cn.exams.databinding.ItemAnswerEditBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class AnswerEditRecyclerAdapter(
    items: MutableList<String>
) : BaseRecyclerAdapter<String, AnswerEditViewHolder>(items) {

    private var selectedIndex = -1

    override fun areContentsTheSame(oldItem: String, newItem: String) = false

    override fun areItemsTheSame(oldItem: String, newItem: String) = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AnswerEditViewHolder(
            ItemAnswerEditBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            { s, index ->
                items[index] = s
            },
            { selectedIndex }
        ) { index ->
            val temp = selectedIndex
            selectedIndex = index
            if (temp != selectedIndex) {
                CoroutineScope(Main).launch {
                    Log.e("Check", items.toString())
                    if (temp >= 0 && temp < items.size) notifyItemChanged(temp)
                }
            }
        }

    override fun onBindViewHolder(holder: AnswerEditViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val pos = position
        holder.binding.etContent.doAfterTextChanged {
            items[pos] = it.toString()
        }
        holder.binding.rbCorrect.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val temp = selectedIndex
                selectedIndex = pos
                notifyItemChanged(temp)
            }
        }
    }

    override fun updateData(newItems: MutableList<String>) {
        selectedIndex = -1
        super.updateData(newItems)
    }

    fun addAnswer() {
        items.add("")
        notifyItemInserted(items.size - 1)
    }

    fun getAnswer() = items

    fun getSelected() = selectedIndex
}