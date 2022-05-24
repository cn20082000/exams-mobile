package com.cn.exams.data.remote.request

import com.cn.exams.util.enumi.ContestScopeEnum
import java.util.*

data class ContestRequest(
    val id: Long? = null,
    val name: String? = null,
    val password: String? = null,
    val questionQuantity: Int? = null,
    val scope: ContestScopeEnum? = null,
    val startAt: Date? = null,
    val endAt: Date? = null,
    val bank: BankRequest? = null
)
