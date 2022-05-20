package com.cn.exams.core

import com.cn.exams.data.DataManager

open class BasePresenter<V: BaseContract.View>(
    protected val view: V
) {
    protected val dataManager = DataManager.getInstance()
}