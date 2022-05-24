package com.cn.exams.ui.intest.report

import com.cn.exams.core.BaseContract

interface ReportContract {
    interface View : BaseContract.View {
        fun requestBack()
    }

    interface Presenter : BaseContract.Presenter {
        fun back()
    }
}