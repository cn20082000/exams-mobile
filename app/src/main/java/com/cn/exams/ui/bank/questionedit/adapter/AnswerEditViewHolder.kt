package com.cn.exams.ui.bank.questionedit.adapter

import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.cn.exams.core.BaseViewHolder
import com.cn.exams.databinding.ItemAnswerEditBinding

class AnswerEditViewHolder(
    val binding: ItemAnswerEditBinding,
    private val onContentEdit: (s: String, index: Int) -> Unit,
    private val selectedIndex: () -> Int,
    private val onItemSelected: (index: Int) -> Unit,
) : BaseViewHolder<String>(binding.root) {

    override fun bindData(data: String, index: Int) {
//        if (data != binding.etContent.text.toString()) {
            binding.etContent.setText(data)
//        }
//        binding.etContent.doAfterTextChanged { text ->
//            onContentEdit(text.toString(), index)
//        }
//        binding.etContent.doOnTextChanged { text, _, _, _ ->
//            Log.e("index", index.toString())
//        }
//        binding.rbCorrect.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) onItemSelected(index)
//        }
        binding.rbCorrect.isChecked = selectedIndex() == index
    }
}