package com.cn.exams.ui.other

import android.view.LayoutInflater
import com.cn.exams.core.BaseFragment
import com.cn.exams.databinding.FragmentOtherBinding
import com.cn.exams.ui.home.HomeFragment

class OtherFragment
    : BaseFragment<FragmentOtherBinding, OtherContract.Presenter>(), OtherContract.View {

    override val setupBinding: (LayoutInflater) -> FragmentOtherBinding
        get() = FragmentOtherBinding::inflate
    override val setupPresenter: () -> OtherContract.Presenter
        get() = { OtherPresenter(this) }
    override val setupViewModel: (OtherContract.Presenter) -> Unit
        get() = binding::setPresenter

    override fun requestLogout() {
        (parentFragment as HomeFragment).toLogin()
    }
}