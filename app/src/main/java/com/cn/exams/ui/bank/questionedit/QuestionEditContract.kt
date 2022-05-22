package com.cn.exams.ui.bank.questionedit

import com.cn.exams.core.BaseContract
import com.cn.exams.lib.data.ErrorEnum

interface QuestionEditContract {
    interface View : BaseContract.View {
        fun requestAddAnswer()
        fun requestAnswerList(): List<String>
        fun requestCorrectAnswer(): Int

        fun requestAction()
        fun actionSuccess()
        fun actionFailed(error: ErrorEnum)
    }

    interface Presenter : BaseContract.Presenter {
        fun addAnswer()

        fun action(content: String?, explanation: String?)
        fun action(
            content: String?,
            explanation: String?,
            answer1: String?,
            answer2: String?,
            answer3: String?,
            answer4: String?
        )
    }
}