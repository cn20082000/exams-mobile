package com.cn.exams.ui.login.register

import com.cn.exams.core.BaseContract
import com.cn.exams.lib.data.ErrorEnum

interface RegisterContract {
    interface View : BaseContract.View {
        fun requestRegister()
        fun registerSuccess()
        fun registerFailed(error: ErrorEnum)
    }
    interface Presenter : BaseContract.Presenter {
        fun register()
        fun register(
            name: String,
            birthStr: String,
            address: String,
            tel: String,
            username: String,
            password: String,
            cfPassword: String
        )
    }
}