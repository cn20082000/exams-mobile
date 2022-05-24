package com.cn.exams.ui.homeinside.contest

import com.cn.exams.core.BaseContract

interface ContestContract {
    interface View : BaseContract.View{
        fun requestTestNow()
    }

    interface Presenter : BaseContract.Presenter {
        fun testNow()
    }
}