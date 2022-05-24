package com.cn.exams.ui.bank.questionlist

import com.cn.exams.core.BasePresenter

class QuestionListPresenter(
    view: QuestionListContract.View,
    private val bankId: Long
) : BasePresenter<QuestionListContract.View>(view), QuestionListContract.Presenter {

    private var infoExpanded = false

    override fun infoToggle() {
        infoExpanded = !infoExpanded
        view.requestInfoToggle(infoExpanded)
    }

    override fun getBankInfo() {
        dataManager.getBank(bankId)
            .onSuccess { view.getBankInfoSuccess(it) }
            .onFailure { view.getBankInfoFailed(it.error) }
            .call()
    }

    override fun searchQuestion(keyword: String?) {
        dataManager.getQuestionByBank(bankId)
            .onSuccess {
                if (keyword.isNullOrBlank()) {
                    view.searchQuestionSuccess(it)
                } else {
                    view.searchQuestionSuccess(it.filter { q ->
                        q.content.lowercase().contains(keyword.lowercase().trim())
                    })
                }
            }
            .onFailure { view.searchQuestionFailed(it.error) }
            .call()
    }

    override fun editBank() {
        view.requestEditBank()
    }

    override fun addQuestion() {
        view.requestAddQuestion()
    }

    override fun addContest() {
        view.requestAddContest()
    }
}