package com.cn.exams.data.repository

import com.cn.exams.data.remote.response.QuestionResponse
import com.cn.exams.lib.data.LoadedAction

interface QuestionRepository {

    fun createQuestion(
        bankId: Long,
        content: String,
        explanation: String,
        correctAnswer: Int,
        answer1: String,
        answer2: String,
        answer3: String,
        answer4: String,
        action: LoadedAction<QuestionResponse>
    )
    fun getQuestionByBank(bankId: Long, action: LoadedAction<List<QuestionResponse>>)
    fun updateQuestion(
        id: Long,
        content: String,
        explanation: String,
        correctAnswer: Int,
        answerId1: Long,
        answer1: String,
        answerId2: Long,
        answer2: String,
        answerId3: Long,
        answer3: String,
        answerId4: Long,
        answer4: String,
        action: LoadedAction<QuestionResponse>
    )

    companion object {
        private val repo: QuestionRepository by lazy { QuestionRepositoryImpl() }

        fun getInstance() = repo
    }
}