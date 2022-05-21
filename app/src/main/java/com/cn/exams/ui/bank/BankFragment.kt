package com.cn.exams.ui.bank

import android.view.LayoutInflater
import com.cn.exams.R
import com.cn.exams.core.BaseFragment
import com.cn.exams.databinding.FragmentBankBinding

class BankFragment
    : BaseFragment<FragmentBankBinding, BankContract.Presenter>(), BankContract.View {

    override val setupBinding: (LayoutInflater) -> FragmentBankBinding
        get() = FragmentBankBinding::inflate
    override val setupPresenter: () -> BankContract.Presenter
        get() = { BankPresenter(this) }
    override val setupViewModel: (BankContract.Presenter) -> Unit
        get() = binding::setPresenter
}