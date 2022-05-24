package com.cn.exams.data.repository

import com.cn.exams.data.remote.api.ContestApi
import com.cn.exams.data.remote.request.BankRequest
import com.cn.exams.data.remote.request.ContestRequest
import com.cn.exams.data.remote.response.Contest4JoinResponse
import com.cn.exams.data.remote.response.ContestResponse
import com.cn.exams.data.remote.response.ReportResponse
import com.cn.exams.lib.data.LoadedAction
import com.cn.exams.util.enumi.ContestScopeEnum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ContestRepositoryImpl : ContestRepository {

    private val contestApi = ContestApi.getInstance()

    override fun createContest(
        name: String,
        password: String,
        quantity: Int,
        startAt: Date,
        endAt: Date?,
        scope: ContestScopeEnum,
        bankId: Long,
        action: LoadedAction<ContestResponse>
    ) {
        CoroutineScope(IO).launch {
            try {
                val response = contestApi.createContest(
                    ContestRequest(
                        null,
                        name,
                        password,
                        quantity,
                        scope,
                        startAt,
                        endAt,
                        BankRequest(null, null, null, bankId)
                    )
                )
                withContext(Main) {
                    action.onResponse(response)
                }
            } catch (ex: Exception) {
                withContext(Main) {
                    action.onException(ex)
                }
            }
        }
    }

    override fun joinContest(
        code: String,
        password: String,
        action: LoadedAction<Contest4JoinResponse>
    ) {
        CoroutineScope(IO).launch {
            try {
                val response = contestApi.joinContest(ContestRequest(
                    null, null, password, null, null, null, null, null, code
                ))
                withContext(Main) {
                    action.onResponse(response)
                }
            } catch (ex: Exception) {
                withContext(Main) {
                    action.onException(ex)
                }
            }
        }
    }

    override fun submitContest(answers: List<Int>, action: LoadedAction<ReportResponse>) {
        CoroutineScope(IO).launch {
            try {
                val response = contestApi.submitContest(answers)
                withContext(Main) {
                    action.onResponse(response)
                }
            } catch (ex: Exception) {
                withContext(Main) {
                    action.onException(ex)
                }
            }
        }
    }
}