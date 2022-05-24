package com.cn.exams.data

import com.cn.exams.data.remote.response.*
import com.cn.exams.data.repository.AuthenticationRepository
import com.cn.exams.data.repository.BankRepository
import com.cn.exams.data.repository.ContestRepository
import com.cn.exams.data.repository.QuestionRepository
import com.cn.exams.lib.data.ResponseObject
import com.cn.exams.util.enumi.BankScopeEnum
import com.cn.exams.util.enumi.ContestScopeEnum
import java.util.*

class DataManagerImpl : DataManager {

    private val authRepo = AuthenticationRepository.getInstance()
    private val bankRepo = BankRepository.getInstance()
    private val questionRepo = QuestionRepository.getInstance()
    private val contestRepo = ContestRepository.getInstance()

    override fun login(username: String, password: String): ResponseObject<LoginResponse> {
        return ResponseObject { authRepo.login(username, password, it) }
    }

    override fun register(
        username: String,
        password: String,
        name: String,
        birth: Date,
        address: String?,
        tel: String
    ): ResponseObject<RegisterResponse> {
        return ResponseObject {
            authRepo.register(
                username,
                password,
                name,
                birth,
                address,
                tel,
                it
            )
        }
    }

    override fun createBank(
        name: String,
        description: String,
        scope: BankScopeEnum
    ): ResponseObject<BankOverviewResponse> {
        return ResponseObject { bankRepo.createBank(name, description, scope, it) }
    }

    override fun getBank(bankId: Long): ResponseObject<BankResponse> {
        return ResponseObject { bankRepo.getBank(bankId, it) }
    }

    override fun getMyBank(): ResponseObject<List<BankOverviewResponse>> {
        return ResponseObject { bankRepo.getMyBank(it) }
    }

    override fun getPublicBank(): ResponseObject<List<BankOverviewResponse>> {
        return ResponseObject { bankRepo.getPublicBank(it) }
    }

    override fun updateBank(
        name: String,
        description: String,
        id: Long
    ): ResponseObject<BankOverviewResponse> {
        return ResponseObject { bankRepo.updateBank(name, description, id, it) }
    }

    override fun createQuestion(
        bankId: Long,
        content: String,
        explanation: String,
        correctAnswer: Int,
        answer1: String,
        answer2: String,
        answer3: String,
        answer4: String
    ): ResponseObject<QuestionResponse> {
        return ResponseObject {
            questionRepo.createQuestion(
                bankId,
                content,
                explanation,
                correctAnswer,
                answer1,
                answer2,
                answer3,
                answer4,
                it
            )
        }
    }

    override fun getQuestionByBank(bankId: Long): ResponseObject<List<QuestionResponse>> {
        return ResponseObject { questionRepo.getQuestionByBank(bankId, it) }
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
        answer4: String
    ): ResponseObject<QuestionResponse> {
        return ResponseObject {
            questionRepo.updateQuestion(
                id,
                content,
                explanation,
                correctAnswer,
                answerId1,
                answer1,
                answerId2,
                answer2,
                answerId3,
                answer3,
                answerId4,
                answer4,
                it
            )
        }
    }

    override fun createContest(
        name: String,
        password: String,
        quantity: Int,
        startAt: Date,
        endAt: Date?,
        scope: ContestScopeEnum,
        bankId: Long
    ): ResponseObject<ContestResponse> {
        return ResponseObject {
            contestRepo.createContest(
                name,
                password,
                quantity,
                startAt,
                endAt,
                scope,
                bankId,
                it
            )
        }
    }
}