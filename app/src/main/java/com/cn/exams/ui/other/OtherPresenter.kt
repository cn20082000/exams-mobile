package com.cn.exams.ui.other

import com.cn.exams.common.App
import com.cn.exams.core.BasePresenter

class OtherPresenter(
    view: OtherContract.View
) : BasePresenter<OtherContract.View>(view), OtherContract.Presenter {

    override fun logout() {
        App.user = null
        view.requestLogout()
    }
}