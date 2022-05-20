package com.cn.exams.ui.login.register

import com.cn.exams.common.Constant
import com.cn.exams.core.BasePresenter
import com.cn.exams.lib.data.ErrorEnum
import java.text.ParseException

class RegisterPresenter(
    view: RegisterContract.View
) : BasePresenter<RegisterContract.View>(view), RegisterContract.Presenter {

    override fun register() {
        view.requestRegister()
    }

    override fun register(
        name: String,
        birthStr: String,
        address: String,
        tel: String,
        username: String,
        password: String,
        cfPassword: String
    ) {
        val birth = run {
            try {
                Constant.dateFormat.parse(birthStr)
            } catch (ex: ParseException) {
                view.registerFailed(ErrorEnum.INVALID_BIRTH_FORMAT)
                return
            }
        }

        if (password != cfPassword) {
            view.registerFailed(ErrorEnum.INVALID_CF_PASSWORD)
            return
        }

        dataManager.register(username, password, name, birth, address, tel)
            .onSuccess { view.registerSuccess() }
            .onFailure { view.registerFailed(it.error) }
            .call()
    }
}