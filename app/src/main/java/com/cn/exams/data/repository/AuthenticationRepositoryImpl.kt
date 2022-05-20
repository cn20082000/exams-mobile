package com.cn.exams.data.repository

import com.cn.exams.data.remote.api.AuthenticationApi
import com.cn.exams.data.remote.request.RegisterRequest
import com.cn.exams.data.remote.response.LoginResponse
import com.cn.exams.data.remote.response.RegisterResponse
import com.cn.exams.lib.data.LoadedAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class AuthenticationRepositoryImpl : AuthenticationRepository {

    private val authApi = AuthenticationApi.getInstance()

    override fun login(username: String, password: String, action: LoadedAction<LoginResponse>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = authApi.login(RegisterRequest(username, password))
                withContext(Dispatchers.Main) {
                    action.onResponse(response)
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    action.onException(ex)
                }
            }
        }
    }

    override fun register(
        username: String,
        password: String,
        name: String,
        birth: Date,
        address: String?,
        tel: String,
        action: LoadedAction<RegisterResponse>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = authApi.register(
                    RegisterRequest(username, password, name, birth, address, tel)
                )
                withContext(Dispatchers.Main) {
                    action.onResponse(response)
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    action.onException(ex)
                }
            }
        }
    }
}