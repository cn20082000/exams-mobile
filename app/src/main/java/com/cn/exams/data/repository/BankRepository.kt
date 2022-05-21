package com.cn.exams.data.repository

import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.lib.data.LoadedAction

interface BankRepository {

    fun getMyBank(action: LoadedAction<List<BankOverviewResponse>>)
    fun getPublicBank(action: LoadedAction<List<BankOverviewResponse>>)

    companion object {
        private val repo: BankRepository by lazy { BankRepositoryImpl() }

        fun getInstance() = repo
    }
}