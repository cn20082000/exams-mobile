package com.cn.exams.ui.intest

import com.cn.exams.core.BaseContract
import com.cn.exams.data.remote.response.ReportResponse
import com.cn.exams.lib.data.ErrorEnum

interface InTestContract {
    interface View : BaseContract.View {
        fun getAnswerList(): List<Int>
        fun requestSubmit()
        fun submitSuccess(report: ReportResponse)
        fun submitFailed(error: ErrorEnum)
    }

    interface Presenter : BaseContract.Presenter {
        fun submit()
    }
}