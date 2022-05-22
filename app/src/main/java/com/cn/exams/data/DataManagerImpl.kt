package com.cn.exams.data

import com.cn.exams.data.remote.response.*
import com.cn.exams.data.repository.AuthenticationRepository
import com.cn.exams.data.repository.BankRepository
import com.cn.exams.data.repository.QuestionRepository
import com.cn.exams.lib.data.ResponseObject
import com.cn.exams.util.enumi.BankScopeEnum
import java.util.*

class DataManagerImpl : DataManager {

    private val authRepo = AuthenticationRepository.getInstance()
    private val bankRepo = BankRepository.getInstance()
    private val questionRepo = QuestionRepository.getInstance()

    override fun login(username: String, password: String): ResponseObject<LoginResponse> {
        return ResponseObject { authRepo.login(username, password, it) }
    }

    override fun register(
        username: String,
        password: String,
        name: String,
        birth: Date,
        address: String?,
        tel: String
    ): ResponseObject<RegisterResponse> {
        return ResponseObject {
            authRepo.register(
                username,
                password,
                name,
                birth,
                address,
                tel,
                it
            )
        }
    }

    override fun createBank(
        name: String,
        description: String,
        scope: BankScopeEnum
    ): ResponseObject<BankOverviewResponse> {
        return ResponseObject { bankRepo.createBank(name, description, scope, it) }
    }

    override fun getBank(bankId: Long): ResponseObject<BankResponse> {
        return ResponseObject { bankRepo.getBank(bankId, it) }
    }

    override fun getMyBank(): ResponseObject<List<BankOverviewResponse>> {
        return ResponseObject { bankRepo.getMyBank(it) }
    }

    override fun getPublicBank(): ResponseObject<List<BankOverviewResponse>> {
        return ResponseObject { bankRepo.getPublicBank(it) }
    }

    override fun updateBank(
        name: String,
        description: String,
        id: Long
    ): ResponseObject<BankOverviewResponse> {
        return ResponseObject { bankRepo.updateBank(name, description, id, it) }
    }

    override fun getQuestionByBank(bankId: Long): ResponseObject<List<QuestionResponse>> {
        return ResponseObject { questionRepo.getQuestionByBank(bankId, it) }
    }
}