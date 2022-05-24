package com.cn.exams.data.repository

import com.cn.exams.data.remote.api.ContestApi
import com.cn.exams.data.remote.request.BankRequest
import com.cn.exams.data.remote.request.ContestRequest
import com.cn.exams.data.remote.response.ContestResponse
import com.cn.exams.lib.data.LoadedAction
import com.cn.exams.util.enumi.ContestScopeEnum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
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
                withContext(Dispatchers.Main) {
                    action.onResponse(response)
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    action.onException(ex)
                }
            }
        }
    }
}