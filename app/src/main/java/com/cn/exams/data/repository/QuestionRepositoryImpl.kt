package com.cn.exams.data.repository

import com.cn.exams.data.remote.api.QuestionApi
import com.cn.exams.data.remote.request.QuestionRequest
import com.cn.exams.data.remote.response.QuestionResponse
import com.cn.exams.lib.data.LoadedAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionRepositoryImpl : QuestionRepository {

    private val questionApi = QuestionApi.getInstance()

    override fun createQuestion(
        bankId: Long,
        content: String,
        explanation: String,
        correctAnswer: Int,
        answer1: String,
        answer2: String,
        answer3: String,
        answer4: String,
        action: LoadedAction<QuestionResponse>
    ) {
        CoroutineScope(IO).launch {
            try {
                val answers = listOf(
                    QuestionRequest.AnswerRequest(null, answer1),
                    QuestionRequest.AnswerRequest(null, answer2),
                    QuestionRequest.AnswerRequest(null, answer3),
                    QuestionRequest.AnswerRequest(null, answer4),
                )
                val request = QuestionRequest(null, content, explanation, correctAnswer, answers)
                val response = questionApi.createQuestion(bankId, request)
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

    override fun updateQuestion(
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
    ) {
        CoroutineScope(IO).launch {
            try {
                val answers = listOf(
                    QuestionRequest.AnswerRequest(answerId1, answer1),
                    QuestionRequest.AnswerRequest(answerId2, answer2),
                    QuestionRequest.AnswerRequest(answerId3, answer3),
                    QuestionRequest.AnswerRequest(answerId4, answer4),
                )
                val request = QuestionRequest(id, content, explanation, correctAnswer, answers)
                val response = questionApi.updateQuestion(request)
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