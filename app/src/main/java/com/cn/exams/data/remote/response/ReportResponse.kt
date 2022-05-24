package com.cn.exams.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class ReportResponse(
    val id: Long,
    val createdAt: Date,
    val updatedAt: Date,
    val score: Int,
    val contest: NormalContestResponse,
    val owner: RegisterResponse
) : Parcelable
