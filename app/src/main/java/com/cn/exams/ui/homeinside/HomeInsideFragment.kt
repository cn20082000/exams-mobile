package com.cn.exams.ui.homeinside

import android.view.LayoutInflater
import com.cn.exams.core.BaseFragment
import com.cn.exams.databinding.FragmentHomeInsideBinding

class HomeInsideFragment
    : BaseFragment<FragmentHomeInsideBinding, HomeInsideContract.Presenter>(), HomeInsideContract.View {

    override val setupBinding: (LayoutInflater) -> FragmentHomeInsideBinding
        get() = FragmentHomeInsideBinding::inflate
    override val setupPresenter: () -> HomeInsideContract.Presenter
        get() = { HomeInsidePresenter(this) }
    override val setupViewModel: (HomeInsideContract.Presenter) -> Unit
        get() = binding::setPresenter
}