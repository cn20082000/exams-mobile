package com.cn.exams.ui.bank

import com.cn.exams.core.BasePresenter
import com.cn.exams.ui.bank.BankContract

class BankPresenter(
    view: BankContract.View
) : BasePresenter<BankContract.View>(view), BankContract.Presenter