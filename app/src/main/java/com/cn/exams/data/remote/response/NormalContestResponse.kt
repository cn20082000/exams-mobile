package com.cn.exams.data.remote.response

import android.os.Parcelable
import com.cn.exams.util.enumi.ContestScopeEnum
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class NormalContestResponse(
    val id: Long,
    val name: String,
    val code: String,
    val questionQuantity: Int,
    val scope: ContestScopeEnum,
    val startAt: Date,
    val endAt: Date?,
    val bank: BankResponse,
    val owner: RegisterResponse
) : Parcelable
