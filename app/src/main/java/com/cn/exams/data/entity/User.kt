package com.cn.exams.data.entity

import com.cn.exams.data.remote.response.LoginResponse

data class User(
    val id: Long,
    val username: String,
    val token: String,
    val detail: UserDetail
) {
    companion object {
        fun from(response: LoginResponse) =
            User(
                response.id,
                response.username,
                response.token,
                UserDetail.from(response.detail)
            )
    }
}