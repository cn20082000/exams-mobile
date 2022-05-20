package com.cn.exams.ui.login

import com.cn.exams.core.BasePresenter

class LoginPresenter(
    view: LoginContract.View
) : BasePresenter<LoginContract.View>(view), LoginContract.Presenter