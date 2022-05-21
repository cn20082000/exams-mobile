package com.cn.exams.ui.login.register

import com.cn.exams.common.Constant
import com.cn.exams.core.BasePresenter
import com.cn.exams.lib.data.ErrorEnum
import java.text.ParseException

class RegisterPresenter(
    view: RegisterContract.View
) : BasePresenter<RegisterContract.View>(view), RegisterContract.Presenter {

    override fun register(
        name: String?,
        birthStr: String?,
        address: String?,
        tel: String?,
        username: String?,
        password: String?,
        cfPassword: String?
    ) {
        val nameC = name ?: ""
        val birthStrC = birthStr ?: ""
        val addressC = address ?: ""
        val telC = tel ?: ""
        val usernameC = username ?: ""
        val passwordC = password ?: ""
        val cfPasswordC = cfPassword ?: ""

        view.requestRegister()
        val birth = run {
            try {
                Constant.dateFormat.parse(birthStrC)
            } catch (ex: ParseException) {
                view.registerFailed(ErrorEnum.INVALID_BIRTH_FORMAT)
                return
            }
        }

        if (passwordC != cfPasswordC) {
            view.registerFailed(ErrorEnum.INVALID_CF_PASSWORD)
            return
        }

        dataManager.register(usernameC, passwordC, nameC, birth, addressC, telC)
            .onSuccess { view.registerSuccess() }
            .onFailure { view.registerFailed(it.error) }
            .call()
    }
}