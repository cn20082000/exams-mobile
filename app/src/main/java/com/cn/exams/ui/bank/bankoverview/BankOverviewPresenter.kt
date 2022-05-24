package com.cn.exams.ui.bank.bankoverview

import com.cn.exams.core.BasePresenter
import com.cn.exams.data.remote.response.BankOverviewResponse

class BankOverviewPresenter(
    view: BankOverviewContract.View
) : BasePresenter<BankOverviewContract.View>(view), BankOverviewContract.Presenter {

    private var isPublic = false

    override fun personalChange(isPublic: Boolean) {
        this.isPublic = isPublic
        view.requestPersonalChange(isPublic)
    }

    override fun search(keyword: String?) {
        view.requestSearch()

        if (isPublic) {
            dataManager.getPublicBank()
                .onSuccess { updateBank(keyword, it) }
                .onFailure { view.searchFailed(it.error) }
                .call()
        } else {
            dataManager.getMyBank()
                .onSuccess { updateBank(keyword, it) }
                .onFailure { view.searchFailed(it.error) }
                .call()
        }
    }

    override fun addBank() {
        view.requestAddBank()
    }

    private fun updateBank(keyword: String?, list: List<BankOverviewResponse>) {
        if (keyword.isNullOrBlank()) {
            view.searchSuccess(list)
        } else {
            view.searchSuccess(list.filter {
                it.name.lowercase().contains(keyword.lowercase().trim())
            })
        }
    }
}