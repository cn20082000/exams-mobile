package com.cn.exams.ui.other

import com.cn.exams.core.BaseContract

interface OtherContract {
    interface View : BaseContract.View {
        fun requestLogout()
    }
    interface Presenter : BaseContract.Presenter {
        fun logout()
    }
}