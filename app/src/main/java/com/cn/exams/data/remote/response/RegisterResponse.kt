package com.cn.exams.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterResponse(
    val id: Long,
    val username: String,
    val detail: LoginResponse.UserDetailResponse,
) : Parcelable