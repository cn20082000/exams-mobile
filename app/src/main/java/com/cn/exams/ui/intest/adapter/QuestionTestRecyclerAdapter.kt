package com.cn.exams.ui.intest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cn.exams.core.BaseRecyclerAdapter
import com.cn.exams.data.remote.response.Contest4JoinResponse
import com.cn.exams.databinding.ItemQuestionInTestBinding

class QuestionTestRecyclerAdapter(
    items: MutableList<Contest4JoinResponse.Question4JoinResponse>
) : BaseRecyclerAdapter<Contest4JoinResponse.Question4JoinResponse, QuestionTestViewHolder>(items) {

    private val answers = MutableList(items.size) { -1 }

    override fun areContentsTheSame(
        oldItem: Contest4JoinResponse.Question4JoinResponse,
        newItem: Contest4JoinResponse.Question4JoinResponse
    ) = false

    override fun areItemsTheSame(
        oldItem: Contest4JoinResponse.Question4JoinResponse,
        newItem: Contest4JoinResponse.Question4JoinResponse
    ) = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuestionTestViewHolder(
        ItemQuestionInTestBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        parent.context
    ) { ans, at ->
        answers[at] = ans
    }

    fun getAnswerList() = answers
}