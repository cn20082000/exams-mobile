package com.cn.exams.data.remote.api

import com.cn.exams.common.RetrofitSingleton
import com.cn.exams.data.remote.request.ContestRequest
import com.cn.exams.data.remote.response.Contest4JoinResponse
import com.cn.exams.data.remote.response.ContestResponse
import com.cn.exams.data.remote.response.ReportResponse
import com.cn.exams.lib.data.ResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ContestApi {

    @POST("/api/contest/create")
    suspend fun createContest(@Body contest: ContestRequest): Response<ResponseModel<ContestResponse>>

    @POST("/api/contest/join")
    suspend fun joinContest(@Body contest: ContestRequest): Response<ResponseModel<Contest4JoinResponse>>

    @POST("/api/contest/submit")
    suspend fun submitContest(@Body answers: List<Int>): Response<ResponseModel<ReportResponse>>

    companion object {
        private val api by lazy { RetrofitSingleton.create(ContestApi::class.java) }

        fun getInstance() = api
    }
}