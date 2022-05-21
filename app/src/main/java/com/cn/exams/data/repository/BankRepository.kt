package com.cn.exams.data.repository

import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.lib.data.LoadedAction
import com.cn.exams.util.enumi.BankScopeEnum

interface BankRepository {

    fun createBank(
        name: String,
        description: String,
        scope: BankScopeEnum,
        action: LoadedAction<BankOverviewResponse>
    )
    fun getMyBank(action: LoadedAction<List<BankOverviewResponse>>)
    fun getPublicBank(action: LoadedAction<List<BankOverviewResponse>>)

    companion object {
        private val repo: BankRepository by lazy { BankRepositoryImpl() }

        fun getInstance() = repo
    }
}