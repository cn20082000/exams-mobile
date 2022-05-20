package com.cn.exams.ui.main

import com.cn.exams.core.BasePresenter

class MainPresenter(
    view: MainContract.View
) : BasePresenter<MainContract.View>(view), MainContract.Presenter