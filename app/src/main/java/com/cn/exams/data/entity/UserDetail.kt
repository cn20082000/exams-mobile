package com.cn.exams.data.entity

import java.time.LocalDateTime

data class UserDetail(
    val id: Long? = null,
    val name: String? = null,
    val birth: LocalDateTime? = null,
    val address: String? = null,
    val tel: String? = null
)
