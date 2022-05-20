package com.cn.exams.common

import java.text.SimpleDateFormat
import java.util.*

object Constant {
    const val BASE_URL = "http://10.0.2.2:8080/"
    const val TOKEN_HEADER = "REQUIRED_TOKEN"
    const val IS_DEBUG = false

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
}