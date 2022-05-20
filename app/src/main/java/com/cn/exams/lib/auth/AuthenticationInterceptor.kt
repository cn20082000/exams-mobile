package com.cn.exams.lib.auth

import com.cn.exams.common.App
import com.cn.exams.common.Constant
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        App.user?.let {
            request = request.newBuilder()
                .addHeader(Constant.TOKEN_HEADER, it.token)
                .build()
        }
        return chain.proceed(request)
    }
}