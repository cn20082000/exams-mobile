package com.cn.exams.common

import com.cn.exams.lib.auth.AuthenticationInterceptor
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

object RetrofitSingleton {

    private var retrofit: Retrofit? = null
    private val gson by lazy {
        GsonBuilder()
            .registerTypeAdapter(Date::class.java, dateDeserializer)
            .registerTypeAdapter(Date::class.java, dateSerializer)
            .create()
    }
    private val dateDeserializer by lazy {
        JsonDeserializer { json, _, _ ->
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
            val formatter2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
            try {
                formatter.parse(json.asJsonPrimitive.asString)
            } catch (ex: Exception) {
                try {
                    formatter2.parse(json.asJsonPrimitive.asString)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    Date()
                }
            }
        }
    }
    private val dateSerializer by lazy {
        JsonSerializer<Date> { date, _, _ ->
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
            JsonPrimitive(formatter.format(date))
        }
    }
    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthenticationInterceptor())
            .build()
    }

    private fun getInstance(): Retrofit =
        retrofit ?: Retrofit.Builder()
            .client(client)
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().also { retrofit = it }

    fun <T> create(service: Class<T>): T =
        getInstance().create(service)
}