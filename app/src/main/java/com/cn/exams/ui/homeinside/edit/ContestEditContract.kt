package com.cn.exams.ui.homeinside.edit

import com.cn.exams.core.BaseContract
import com.cn.exams.data.remote.response.BankResponse
import com.cn.exams.data.remote.response.ContestResponse
import com.cn.exams.lib.data.ErrorEnum

interface ContestEditContract {
    interface View : BaseContract.View {
        fun getBankInfoSuccess(bank: BankResponse)
        fun getBankInfoFailed(error: ErrorEnum)

        fun requestInfoToggle(isChecked: Boolean)
        fun requestPersonalChange(isPublic: Boolean)

        fun requestAction()
        fun actionSuccess(contest: ContestResponse)
        fun actionFailed(error: ErrorEnum)
    }

    interface Presenter : BaseContract.Presenter {
        fun getBankInfo(bankId: Long)

        fun infoToggle()
        fun personalChange(isPublic: Boolean)

        fun action(
            name: String?,
            password: String?,
            quantity: String?,
            startAt: String?,
            endAt: String?,
            isPublic: Boolean?
        )
    }
}