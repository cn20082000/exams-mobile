package com.cn.exams.data.remote.response

import java.util.*

data class LoginResponse(
    val id: Long,
    val username: String,
    val token: String,
    val detail: UserDetailResponse,
) {

    data class UserDetailResponse(
        val id: Long,
        val name: String,
        val birth: Date,
        val address: String = "",
        val tel: String,
    )
}
