package com.cn.exams.data.repository

import com.cn.exams.data.remote.api.QuestionApi
import com.cn.exams.data.remote.response.QuestionResponse
import com.cn.exams.lib.data.LoadedAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionRepositoryImpl : QuestionRepository {

    private val questionApi = QuestionApi.getInstance()

    override fun getQuestionByBank(bankId: Long, action: LoadedAction<List<QuestionResponse>>) {
        CoroutineScope(IO).launch {
            try {
                val response = questionApi.getQuestionByBank(bankId)
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