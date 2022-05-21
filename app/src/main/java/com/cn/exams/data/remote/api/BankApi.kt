package com.cn.exams.data.remote.api

import com.cn.exams.common.RetrofitSingleton
import com.cn.exams.data.remote.request.BankRequest
import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.data.remote.response.BankResponse
import com.cn.exams.lib.data.ResponseModel
import retrofit2.Response
import retrofit2.http.*

interface BankApi {

    @POST("/api/bank/create")
    suspend fun createBank(@Body request: BankRequest): Response<ResponseModel<BankOverviewResponse>>

    @GET("/api/bank")
    suspend fun getBank(@Query("bankId") bankId: Long): Response<ResponseModel<BankResponse>>

    @GET("/api/bank/my")
    suspend fun getMyBank(): Response<ResponseModel<List<BankOverviewResponse>>>

    @GET("/api/bank/public")
    suspend fun getPublicBank(): Response<ResponseModel<List<BankOverviewResponse>>>

    @PUT("/api/bank/update")
    suspend fun updateBank(@Body request: BankRequest): Response<ResponseModel<BankOverviewResponse>>

    companion object {
        private val api by lazy { RetrofitSingleton.create(BankApi::class.java) }

        fun getInstance() = api
    }
}