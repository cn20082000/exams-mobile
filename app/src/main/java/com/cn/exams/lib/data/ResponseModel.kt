package com.cn.exams.lib.data

data class ResponseModel<T>(
    val data: T? = null,
    val error: ErrorResponseModel? = null
)