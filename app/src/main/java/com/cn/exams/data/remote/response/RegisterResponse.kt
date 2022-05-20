package com.cn.exams.data.remote.response

data class RegisterResponse(
    val id: Long,
    val username: String,
    val detail: LoginResponse.UserDetailResponse,
)