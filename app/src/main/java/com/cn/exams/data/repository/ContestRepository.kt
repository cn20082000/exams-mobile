package com.cn.exams.data.repository

import com.cn.exams.data.remote.response.ContestResponse
import com.cn.exams.lib.data.LoadedAction
import com.cn.exams.util.enumi.ContestScopeEnum
import java.util.*

interface ContestRepository {

    fun createContest(
        name: String,
        password: String,
        quantity: Int,
        startAt: Date,
        endAt: Date?,
        scope: ContestScopeEnum,
        bankId: Long,
        action: LoadedAction<ContestResponse>
    )

    companion object {
        private val repo: ContestRepository by lazy { ContestRepositoryImpl() }

        fun getInstance() = repo
    }
}