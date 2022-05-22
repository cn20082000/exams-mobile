package com.cn.exams.data.remote.api

import com.cn.exams.common.RetrofitSingleton
import com.cn.exams.data.remote.request.QuestionRequest
import com.cn.exams.data.remote.response.QuestionResponse
import com.cn.exams.lib.data.ResponseModel
import retrofit2.Response
import retrofit2.http.*

interface QuestionApi {

    @POST("/api/question/create")
    suspend fun createQuestion(@Query("bankId") bankId: Long, @Body question: QuestionRequest): Response<ResponseModel<QuestionResponse>>

    @GET("/api/question")
    suspend fun getQuestionByBank(@Query("bankId") bankId: Long): Response<ResponseModel<List<QuestionResponse>>>

    @PUT("/api/question/update")
    suspend fun updateQuestion(@Body question: QuestionRequest): Response<ResponseModel<QuestionResponse>>

    companion object {
        private val api by lazy { RetrofitSingleton.create(QuestionApi::class.java) }

        fun getInstance() = api
    }
}