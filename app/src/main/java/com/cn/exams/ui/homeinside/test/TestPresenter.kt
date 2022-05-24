package com.cn.exams.ui.homeinside.test

import com.cn.exams.core.BasePresenter

class TestPresenter(
    view: TestContract.View
) : BasePresenter<TestContract.View>(view), TestContract.Presenter {

    override fun join(code: String?, password: String?) {
        val codeC = code ?: ""
        val passwordC = password ?: ""

        dataManager.joinContest(codeC, passwordC)
            .onSuccess { view.joinSuccess(it) }
            .onFailure { view.joinFailed(it.error) }
            .call()
    }
}