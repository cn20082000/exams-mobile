package com.cn.exams.data.repository

import com.cn.exams.data.remote.response.QuestionResponse
import com.cn.exams.lib.data.LoadedAction

interface QuestionRepository {

    fun getQuestionByBank(bankId: Long, action: LoadedAction<List<QuestionResponse>>)

    companion object {
        private val repo: QuestionRepository by lazy { QuestionRepositoryImpl() }

        fun getInstance() = repo
    }
}