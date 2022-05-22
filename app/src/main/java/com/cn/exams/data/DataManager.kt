package com.cn.exams.data

import com.cn.exams.data.remote.response.*
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
    fun getBank(bankId: Long): ResponseObject<BankResponse>
    fun getMyBank(): ResponseObject<List<BankOverviewResponse>>
    fun getPublicBank(): ResponseObject<List<BankOverviewResponse>>
    fun updateBank(name: String, description: String, id: Long): ResponseObject<BankOverviewResponse>

    fun getQuestionByBank(bankId: Long): ResponseObject<List<QuestionResponse>>

    companion object {
        private val data by lazy { DataManagerImpl() }

        fun getInstance() = data
    }
}