package com.cn.exams.ui.bank.questionlist

import com.cn.exams.core.BasePresenter

class QuestionListPresenter(
    view: QuestionListContract.View
) : BasePresenter<QuestionListContract.View>(view), QuestionListContract.Presenter {

    private var infoExpanded = false

    override fun infoToggle() {
        infoExpanded = !infoExpanded
        view.requestInfoToggle(infoExpanded)
    }

    override fun getBankInfo(bankId: Long) {
        dataManager.getBank(bankId)
            .onSuccess { view.getBankInfoSuccess(it) }
            .onFailure { view.getBankInfoFailed(it.error) }
            .call()
    }

    override fun editBank() {
        view.requestEditBank()
    }
}