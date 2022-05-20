package com.cn.exams.ui.login

import com.cn.exams.core.BaseContract
import com.cn.exams.lib.data.ErrorEnum

interface LoginContract {
    interface View : BaseContract.View {
        fun requestLogin()
        fun loginSuccess()
        fun loginFailed(error: ErrorEnum)

        fun requestRegister()
    }

    interface Presenter : BaseContract.Presenter {
        fun login()
        fun login(username: String, password: String)

        fun register()
    }
}