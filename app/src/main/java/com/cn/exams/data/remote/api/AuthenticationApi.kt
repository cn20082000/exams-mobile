package com.cn.exams.data.remote.api

import com.cn.exams.common.RetrofitSingleton
import com.cn.exams.data.remote.request.RegisterRequest
import com.cn.exams.data.remote.response.LoginResponse
import com.cn.exams.data.remote.response.RegisterResponse
import com.cn.exams.lib.data.ResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {

    @POST("/api/auth/login")
    suspend fun login(@Body request: RegisterRequest): Response<ResponseModel<LoginResponse>>

    @POST("/api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<ResponseModel<RegisterResponse>>

    companion object {
        private val api by lazy { RetrofitSingleton.create(AuthenticationApi::class.java) }

        fun getInstance() = api
    }
}