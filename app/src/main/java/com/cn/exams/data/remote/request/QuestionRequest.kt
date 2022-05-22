package com.cn.exams.data.remote.request

data class QuestionRequest(
    val id: Long? = null,
    val content: String? = null,
    val explanation: String? = null,
    val correctAnswer: Int? = null,
    val answer: List<AnswerRequest>? = null
) {
    data class AnswerRequest(
        val id: Long? = null,
        val content: String? = null
    )
}
