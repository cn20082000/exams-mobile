package com.cn.exams.ui.bank.questionlist

import com.cn.exams.core.BaseContract
import com.cn.exams.data.remote.response.BankResponse
import com.cn.exams.lib.data.ErrorEnum

interface QuestionListContract {
    interface View : BaseContract.View {
        fun requestInfoToggle(isChecked: Boolean)

        fun getBankInfoSuccess(bank: BankResponse)
        fun getBankInfoFailed(error: ErrorEnum)

        fun requestEditBank()
    }
    interface Presenter : BaseContract.Presenter {
        fun infoToggle()

        fun getBankInfo(bankId: Long)

        fun editBank()
    }
}