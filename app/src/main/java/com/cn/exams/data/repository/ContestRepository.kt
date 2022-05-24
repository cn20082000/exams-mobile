package com.cn.exams.data.repository

import com.cn.exams.data.remote.response.Contest4JoinResponse
import com.cn.exams.data.remote.response.ContestResponse
import com.cn.exams.data.remote.response.ReportResponse
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
    fun joinContest(
        code: String,
        password: String,
        action: LoadedAction<Contest4JoinResponse>
    )
    fun submitContest(answers: List<Int>, action: LoadedAction<ReportResponse>)

    companion object {
        private val repo: ContestRepository by lazy { ContestRepositoryImpl() }

        fun getInstance() = repo
    }
}