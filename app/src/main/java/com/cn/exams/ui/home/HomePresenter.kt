package com.cn.exams.ui.home

import com.cn.exams.core.BasePresenter

class HomePresenter(
    view: HomeContract.View
) : BasePresenter<HomeContract.View>(view), HomeContract.Presenter {
}