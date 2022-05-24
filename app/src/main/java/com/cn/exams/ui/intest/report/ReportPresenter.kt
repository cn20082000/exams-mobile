package com.cn.exams.ui.intest.report

import com.cn.exams.core.BasePresenter

class ReportPresenter(
    view: ReportContract.View
) : BasePresenter<ReportContract.View>(view), ReportContract.Presenter {

    override fun back() {
        view.requestBack()
    }
}