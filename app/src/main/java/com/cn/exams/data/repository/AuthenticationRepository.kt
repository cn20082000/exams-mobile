package com.cn.exams.data.repository

import com.cn.exams.data.remote.response.LoginResponse
import com.cn.exams.data.remote.response.RegisterResponse
import com.cn.exams.lib.data.LoadedAction
import java.util.*

interface AuthenticationRepository {
    fun login(username: String, password: String, action: LoadedAction<LoginResponse>)
    fun register(
        username: String,
        password: String,
        name: String,
        birth: Date,
        address: String?,
        tel: String,
        action: LoadedAction<RegisterResponse>
    )

    companion object {
        private val repo: AuthenticationRepository by lazy { AuthenticationRepositoryImpl() }

        fun getInstance() = repo
    }
}