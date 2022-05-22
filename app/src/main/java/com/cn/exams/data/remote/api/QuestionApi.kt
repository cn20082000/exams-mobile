package com.cn.exams.data.remote.api

import com.cn.exams.common.RetrofitSingleton
import com.cn.exams.data.remote.response.QuestionResponse
import com.cn.exams.lib.data.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionApi {

    @GET("/api/question")
    suspend fun getQuestionByBank(@Query("bankId") bankId: Long): Response<ResponseModel<List<QuestionResponse>>>

    companion object {
        private val api by lazy { RetrofitSingleton.create(QuestionApi::class.java) }

        fun getInstance() = api
    }
}