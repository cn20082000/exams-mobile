package com.cn.exams.ui.bank.questionlist.adapter

import android.content.Context
import com.cn.exams.R
import com.cn.exams.common.Constant
import com.cn.exams.core.BaseViewHolder
import com.cn.exams.data.remote.response.QuestionResponse
import com.cn.exams.databinding.ItemQuestionBinding

class QuestionViewHolder(
    private val binding: ItemQuestionBinding,
    private val context: Context
) : BaseViewHolder<QuestionResponse>(binding.root) {

    override fun bindData(data: QuestionResponse, index: Int) {
        binding.tvContent.text = data.content
        binding.tvUpdated.text = context.resources.getString(
            R.string.updated_at_question,
            Constant.dateFormat.format(data.updatedAt)
        )
    }
}