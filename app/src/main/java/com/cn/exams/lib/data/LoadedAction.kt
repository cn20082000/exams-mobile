package com.cn.exams.lib.data

import com.google.gson.Gson
import com.google.gson.JsonParser
import retrofit2.Response

class LoadedAction<T>(
    private val onSuccess: (T) -> Unit,
    private val onFailure: (ErrorResponseModel) -> Unit,
) {

    fun onResponse(response: ResponseModel<T>) {
        response.data?.let(onSuccess)
            ?: response.error?.let {
                it.log()
                onFailure(it)
            }
            ?: run {
                val er = ErrorResponseModel(-1, ErrorEnum.UNKNOWN_ERROR, "Null response")
                er.log()
                onFailure(er)
            }
    }

    fun onResponse(response: Response<ResponseModel<T>>) {
        if (response.isSuccessful) {
            response.body()?.let { it.data?.let(onSuccess) }
        } else {
            response.errorBody()?.let {
                val errorObj = JsonParser().parse(it.string())
                    .asJsonObject
                    .getAsJsonObject("error")
                val error = Gson().fromJson(errorObj, ErrorResponseModel::class.java)
                error.log()
                onFailure(error)
            } ?: run {
                val er = ErrorResponseModel(-1, ErrorEnum.UNKNOWN_ERROR, "Null response")
                er.log()
                onFailure(er)
            }
        }
    }

    fun onException(ex: Throwable) {
        val er = ErrorResponseModel(-1, ErrorEnum.LOCAL_ERROR, ex.message ?: "", ex.stackTraceToString())
        er.log()
        onFailure(er)
    }
}