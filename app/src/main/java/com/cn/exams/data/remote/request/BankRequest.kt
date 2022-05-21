package com.cn.exams.data.remote.request

import com.cn.exams.util.enumi.BankScopeEnum

data class BankRequest(
    val name: String? = null,
    val description: String? = null,
    val scope: BankScopeEnum? = null
)
