package com.cn.exams.data.remote.response

import java.util.*

data class QuestionResponse(
    val id: Long,
    val createdAt: Date,
    val updatedAt: Date,
    val content: String,
    val explanation: String?,
    val correctAnswer: Int,
    val answer: List<AnswerResponse>
) {

    data class AnswerResponse(
        val id: Long,
        val content: String
    )
}
