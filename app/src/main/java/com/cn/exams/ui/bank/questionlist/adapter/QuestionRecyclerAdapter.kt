package com.cn.exams.ui.bank.questionlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cn.exams.core.BaseRecyclerAdapter
import com.cn.exams.data.remote.response.QuestionResponse
import com.cn.exams.databinding.ItemQuestionBinding

class QuestionRecyclerAdapter(
    items: MutableList<QuestionResponse>,
    private val onItemClick: (QuestionResponse) -> Unit
) : BaseRecyclerAdapter<QuestionResponse, QuestionViewHolder>(items) {

    override fun areContentsTheSame(oldItem: QuestionResponse, newItem: QuestionResponse) =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: QuestionResponse, newItem: QuestionResponse) =
        oldItem.id == newItem.id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuestionViewHolder(
        ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        parent.context,
        onItemClick
    )
}