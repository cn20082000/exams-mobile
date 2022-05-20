package com.cn.exams.lib.data

import android.util.Log

data class ErrorResponseModel(
    val statusCode: Int,
    val error: ErrorEnum,
    val message: String,
    val stacktrace: String? = null,
) {
    fun log() {
        Log.e(error.toString(), stacktrace ?: message)
    }
}