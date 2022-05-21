package com.cn.exams.data.repository

import com.cn.exams.data.remote.api.BankApi
import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.lib.data.LoadedAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BankRepositoryImpl : BankRepository {

    private val bankApi = BankApi.getInstance()

    override fun getMyBank(action: LoadedAction<List<BankOverviewResponse>>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = bankApi.getMyBank()
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

    override fun getPublicBank(action: LoadedAction<List<BankOverviewResponse>>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = bankApi.getPublicBank()
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