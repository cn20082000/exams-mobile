package com.cn.exams.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class QuestionResponse(
    val id: Long,
    val createdAt: Date,
    val updatedAt: Date,
    val content: String,
    val explanation: String?,
    val correctAnswer: Int,
    val answer: List<AnswerResponse>
) : Parcelable {

    @Parcelize
    data class AnswerResponse(
        val id: Long,
        val content: String
    ) : Parcelable
}
