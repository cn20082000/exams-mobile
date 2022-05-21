package com.cn.exams.ui.login

import com.cn.exams.common.App
import com.cn.exams.core.BasePresenter
import com.cn.exams.data.entity.User

class LoginPresenter(
    view: LoginContract.View
) : BasePresenter<LoginContract.View>(view), LoginContract.Presenter {

    override fun login(username: String?, password: String?) {
        val usernameC = username ?: ""
        val passwordC = password ?: ""

        view.requestLogin()
        dataManager.login(usernameC, passwordC)
            .onSuccess {
                App.user = User.from(it)
                view.loginSuccess()
            }
            .onFailure { view.loginFailed(it.error) }
            .call()
    }

    override fun register() {
        view.requestRegister()
    }
}