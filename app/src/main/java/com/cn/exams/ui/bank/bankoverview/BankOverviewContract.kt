package com.cn.exams.ui.bank.bankoverview

import com.cn.exams.core.BaseContract
import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.lib.data.ErrorEnum

interface BankOverviewContract {
    interface View : BaseContract.View {
        fun requestPersonalChange(isPublic: Boolean)

        fun requestSearch()
        fun searchSuccess(list: List<BankOverviewResponse>)
        fun searchFailed(error: ErrorEnum)
    }

    interface Presenter : BaseContract.Presenter {
        fun personalChange(isPublic: Boolean)

        fun search(keyword: String?)
    }
}