package com.cn.exams.data.repository

import com.cn.exams.data.remote.api.BankApi
import com.cn.exams.data.remote.request.BankRequest
import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.data.remote.response.BankResponse
import com.cn.exams.lib.data.LoadedAction
import com.cn.exams.util.enumi.BankScopeEnum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BankRepositoryImpl : BankRepository {

    private val bankApi = BankApi.getInstance()

    override fun createBank(
        name: String,
        description: String,
        scope: BankScopeEnum,
        action: LoadedAction<BankOverviewResponse>
    ) {
        CoroutineScope(IO).launch {
            try {
                val response = bankApi.createBank(BankRequest(
                    name, description, scope
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

    override fun getBank(bankId: Long, action: LoadedAction<BankResponse>) {
        CoroutineScope(IO).launch {
            try {
                val response = bankApi.getBank(bankId)
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

    override fun getMyBank(action: LoadedAction<List<BankOverviewResponse>>) {
        CoroutineScope(IO).launch {
            try {
                val response = bankApi.getMyBank()
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

    override fun getPublicBank(action: LoadedAction<List<BankOverviewResponse>>) {
        CoroutineScope(IO).launch {
            try {
                val response = bankApi.getPublicBank()
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

    override fun updateBank(
        name: String,
        description: String,
        id: Long,
        action: LoadedAction<BankOverviewResponse>
    ) {
        CoroutineScope(IO).launch {
            try {
                val response = bankApi.updateBank(
                    BankRequest(name, description, null, id)
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
}