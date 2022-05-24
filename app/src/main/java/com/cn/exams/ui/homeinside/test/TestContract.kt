package com.cn.exams.ui.homeinside.test

import com.cn.exams.core.BaseContract
import com.cn.exams.data.remote.response.Contest4JoinResponse
import com.cn.exams.lib.data.ErrorEnum

interface TestContract {
    interface View : BaseContract.View{
        fun requestJoin()
        fun joinSuccess(contest: Contest4JoinResponse)
        fun joinFailed(error: ErrorEnum)
    }
    interface Presenter : BaseContract.Presenter {
        fun join(code: String?, password: String?)
    }
}