package com.cn.exams.ui.intest

import com.cn.exams.core.BasePresenter

class InTestPresenter(
    view: InTestContract.View
) : BasePresenter<InTestContract.View>(view), InTestContract.Presenter {

    override fun submit() {
        view.requestSubmit()
        dataManager.submitContest(view.getAnswerList())
            .onSuccess { view.submitSuccess(it) }
            .onFailure { view.submitFailed(it.error) }
            .call()
    }
}