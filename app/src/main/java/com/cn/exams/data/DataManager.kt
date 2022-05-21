package com.cn.exams.data

import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.data.remote.response.LoginResponse
import com.cn.exams.data.remote.response.RegisterResponse
import com.cn.exams.lib.data.ResponseObject
import com.cn.exams.util.enumi.BankScopeEnum
import java.util.*

interface DataManager {

    fun login(username: String, password: String): ResponseObject<LoginResponse>
    fun register(
        username: String,
        password: String,
        name: String,
        birth: Date,
        address: String?,
        tel: String
    ): ResponseObject<RegisterResponse>

    fun createBank(
        name: String,
        description: String,
        scope: BankScopeEnum
    ): ResponseObject<BankOverviewResponse>
    fun getMyBank(): ResponseObject<List<BankOverviewResponse>>
    fun getPublicBank(): ResponseObject<List<BankOverviewResponse>>

    companion object {
        private val data by lazy { DataManagerImpl() }

        fun getInstance() = data
    }
}