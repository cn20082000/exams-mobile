package com.cn.exams.ui.bank.edit

import com.cn.exams.core.BasePresenter
import com.cn.exams.util.enumi.BankScopeEnum

class BankEditPresenter(
    view: BankEditContract.View,
    private val mode: Int
) : BasePresenter<BankEditContract.View>(view), BankEditContract.Presenter {

    override fun personalChange(isPublic: Boolean) {
        view.requestPersonalChange(isPublic)
    }

    override fun action(name: String?, description: String?, scope: Boolean?) {
        val nameC = name ?: ""
        val descriptionC = description ?: ""
        val scopeC = scope ?: false

        if (mode == BankEditFragment.MODE_ADD) {
            dataManager.createBank(
                nameC,
                descriptionC,
                if (scopeC) BankScopeEnum.PUBLIC else BankScopeEnum.PERSONAL
            ).onSuccess { view.actionSuccess() }
                .onFailure { view.actionFailed(it.error) }
                .call()
        }
    }
}