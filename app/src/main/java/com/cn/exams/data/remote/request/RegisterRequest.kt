package com.cn.exams.data.remote.request

import java.util.*

data class RegisterRequest(
    val username: String? = null,
    val password: String? = null,
    val name: String? = null,
    val birth: Date? = null,
    val address: String? = null,
    val tel: String? = null
)
