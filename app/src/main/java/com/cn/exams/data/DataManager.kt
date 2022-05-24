package com.cn.exams.data

import com.cn.exams.data.remote.response.*
import com.cn.exams.lib.data.ResponseObject
import com.cn.exams.util.enumi.BankScopeEnum
import com.cn.exams.util.enumi.ContestScopeEnum
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

    fun createQuestion(
        bankId: Long,
        content: String,
        explanation: String,
        correctAnswer: Int,
        answer1: String,
        answer2: String,
        answer3: String,
        answer4: String,
    ): ResponseObject<QuestionResponse>
    fun getQuestionByBank(bankId: Long): ResponseObject<List<QuestionResponse>>
    fun updateQuestion(
        id: Long,
        content: String,
        explanation: String,
        correctAnswer: Int,
        answerId1: Long,
        answer1: String,
        answerId2: Long,
        answer2: String,
        answerId3: Long,
        answer3: String,
        answerId4: Long,
        answer4: String,
    ): ResponseObject<QuestionResponse>

    fun createContest(
        name: String,
        password: String,
        quantity: Int,
        startAt: Date,
        endAt: Date?,
        scope: ContestScopeEnum,
        bankId: Long,
    ): ResponseObject<ContestResponse>

    companion object {
        private val data by lazy { DataManagerImpl() }

        fun getInstance() = data
    }
}