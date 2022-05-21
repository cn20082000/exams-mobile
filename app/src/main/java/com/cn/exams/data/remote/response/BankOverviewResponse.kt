package com.cn.exams.data.remote.response

import com.cn.exams.util.enumi.BankScopeEnum
import java.util.*

data class BankOverviewResponse(
    val id: Long,
    val createdAt: Date,
    val updatedAt: Date,
    val name: String,
    val description: String?,
    val scope: BankScopeEnum,
    val owner: RegisterResponse
)
