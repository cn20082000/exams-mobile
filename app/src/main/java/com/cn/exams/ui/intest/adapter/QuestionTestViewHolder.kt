package com.cn.exams.ui.intest.adapter

import android.content.Context
import android.view.View
import com.cn.exams.R
import com.cn.exams.core.BaseViewHolder
import com.cn.exams.data.remote.response.Contest4JoinResponse
import com.cn.exams.databinding.ItemQuestionInTestBinding

class QuestionTestViewHolder(
    private val binding: ItemQuestionInTestBinding,
    private val context: Context,
    private val onAnswerChange: (ans: Int, at: Int) -> Unit
) : BaseViewHolder<Contest4JoinResponse.Question4JoinResponse>(binding.root) {

    override fun bindData(data: Contest4JoinResponse.Question4JoinResponse, index: Int) {
        binding.tvQuestion.text = context.getString(R.string.question_template, index + 1, data.content)

        if (data.answer.isNotEmpty()) {
            binding.rbAnswer1.text = data.answer[0].content
            binding.rbAnswer1.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) onAnswerChange(0, index)
            }
        } else {
            binding.rbAnswer1.visibility = View.GONE
        }
        if (data.answer.size >= 2) {
            binding.rbAnswer2.text = data.answer[1].content
            binding.rbAnswer2.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) onAnswerChange(1, index)
            }
        } else {
            binding.rbAnswer2.visibility = View.GONE
        }
        if (data.answer.size >= 3) {
            binding.rbAnswer3.text = data.answer[2].content
            binding.rbAnswer3.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) onAnswerChange(2, index)
            }
        } else {
            binding.rbAnswer3.visibility = View.GONE
        }
        if (data.answer.size >= 4) {
            binding.rbAnswer4.text = data.answer[3].content
            binding.rbAnswer4.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) onAnswerChange(3, index)
            }
        } else {
            binding.rbAnswer4.visibility = View.GONE
        }
    }
}