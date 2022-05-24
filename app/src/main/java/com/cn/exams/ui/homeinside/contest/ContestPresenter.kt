package com.cn.exams.ui.homeinside.contest

import com.cn.exams.core.BasePresenter

class ContestPresenter(
    view: ContestContract.View
) : BasePresenter<ContestContract.View>(view), ContestContract.Presenter {

    override fun testNow() {
        view.requestTestNow()
    }
}