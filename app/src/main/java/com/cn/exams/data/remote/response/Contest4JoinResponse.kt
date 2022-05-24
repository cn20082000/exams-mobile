package com.cn.exams.data.remote.response

import android.os.Parcelable
import com.cn.exams.util.enumi.ContestScopeEnum
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Contest4JoinResponse(
    val id: Long,
    val name: String,
    val code: String,
    val questionQuantity: Int,
    val scope: ContestScopeEnum,
    val startAt: Date,
    val endAt: Date?,
    val owner: RegisterResponse,
    val questions: List<Question4JoinResponse>
) : Parcelable {
    @Parcelize
    data class Question4JoinResponse(
        val content: String,
        val answer: List<Answer4JoinResponse>
    ) : Parcelable

    @Parcelize
    data class Answer4JoinResponse(
        val content: String,
    ) : Parcelable
}
