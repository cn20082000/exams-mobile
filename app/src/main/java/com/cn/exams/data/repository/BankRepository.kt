package com.cn.exams.data.repository

import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.data.remote.response.BankResponse
import com.cn.exams.lib.data.LoadedAction
import com.cn.exams.util.enumi.BankScopeEnum

interface BankRepository {

    fun createBank(
        name: String,
        description: String,
        scope: BankScopeEnum,
        action: LoadedAction<BankOverviewResponse>
    )
    fun getBank(bankId: Long, action: LoadedAction<BankResponse>)
    fun getMyBank(action: LoadedAction<List<BankOverviewResponse>>)
    fun getPublicBank(action: LoadedAction<List<BankOverviewResponse>>)
    fun updateBank(name: String, description: String, id: Long, action: LoadedAction<BankOverviewResponse>)

    companion object {
        private val repo: BankRepository by lazy { BankRepositoryImpl() }

        fun getInstance() = repo
    }
}