package com.cn.exams.data.entity

data class User(
    val id: Long? = null,
    val username: String? = null,
    val token: String? = null,
    val detail: UserDetail? = null
)