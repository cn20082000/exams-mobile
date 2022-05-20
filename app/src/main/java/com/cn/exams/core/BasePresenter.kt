package com.cn.exams.core

open class BasePresenter<V: BaseContract.View>(
    protected val view: V
)