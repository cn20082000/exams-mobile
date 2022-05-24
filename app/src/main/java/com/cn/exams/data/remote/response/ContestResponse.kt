package com.cn.exams.data.remote.response

import com.cn.exams.util.enumi.ContestScopeEnum
import java.util.*

data class ContestResponse(
    val id: Long,
    val name: String,
    val code: String,
    val password: String,
    val questionQuantity: Int,
    val scope: ContestScopeEnum,
    val startAt: Date,
    val endAt: Date?,
    val bank: BankResponse,
    val owner: RegisterResponse
)