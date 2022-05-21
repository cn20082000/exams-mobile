package com.cn.exams.ui.bank.edit

import com.cn.exams.core.BaseContract
import com.cn.exams.lib.data.ErrorEnum

interface BankEditContract {
    interface View : BaseContract.View {
        fun requestPersonalChange(isPublic: Boolean)

        fun requestAction()
        fun actionSuccess()
        fun actionFailed(error: ErrorEnum)
    }

    interface Presenter : BaseContract.Presenter {
        fun personalChange(isPublic: Boolean)

        fun action(name: String?, description: String?, scope: Boolean?)
    }
}