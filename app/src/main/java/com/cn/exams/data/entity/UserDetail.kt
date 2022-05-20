package com.cn.exams.data.entity

import com.cn.exams.data.remote.response.LoginResponse
import java.util.*

data class UserDetail(
    val id: Long,
    val name: String,
    val birth: Date,
    val address: String?,
    val tel: String
) {
    companion object {
        fun from(response: LoginResponse.UserDetailResponse) =
            UserDetail(
                response.id,
                response.name,
                response.birth,
                response.address,
                response.tel
            )
    }
}
