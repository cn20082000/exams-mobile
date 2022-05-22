package com.cn.exams.ui.bank.questionlist

import com.cn.exams.core.BaseContract
import com.cn.exams.data.remote.response.BankResponse
import com.cn.exams.data.remote.response.QuestionResponse
import com.cn.exams.lib.data.ErrorEnum

interface QuestionListContract {
    interface View : BaseContract.View {
        fun requestInfoToggle(isChecked: Boolean)

        fun getBankInfoSuccess(bank: BankResponse)
        fun getBankInfoFailed(error: ErrorEnum)
        fun searchQuestionSuccess(list: List<QuestionResponse>)
        fun searchQuestionFailed(error: ErrorEnum)

        fun requestEditBank()

        fun requestAddQuestion()
    }
    interface Presenter : BaseContract.Presenter {
        fun infoToggle()

        fun getBankInfo()
        fun searchQuestion(keyword: String?)

        fun editBank()

        fun addQuestion()
    }
}