package com.cn.exams.data

import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.data.remote.response.LoginResponse
import com.cn.exams.data.remote.response.RegisterResponse
import com.cn.exams.data.repository.AuthenticationRepository
import com.cn.exams.data.repository.BankRepository
import com.cn.exams.lib.data.ResponseObject
import java.util.*

class DataManagerImpl : DataManager {

    private val authRepo = AuthenticationRepository.getInstance()
    private val bankRepo = BankRepository.getInstance()

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

    override fun getMyBank(): ResponseObject<List<BankOverviewResponse>> {
        return ResponseObject { bankRepo.getMyBank(it) }
    }

    override fun getPublicBank(): ResponseObject<List<BankOverviewResponse>> {
        return ResponseObject { bankRepo.getPublicBank(it) }
    }
}