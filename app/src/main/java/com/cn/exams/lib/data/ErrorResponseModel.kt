package com.cn.exams.lib.data

import android.util.Log
import com.cn.exams.common.Constant

data class ErrorResponseModel(
    val statusCode: Int,
    val error: ErrorEnum,
    val message: String,
    val stacktrace: String? = null,
) {
    fun log() {
        Log.e(error.toString(),
            if (Constant.IS_DEBUG) stacktrace
                ?: message else ""
        )
    }
}