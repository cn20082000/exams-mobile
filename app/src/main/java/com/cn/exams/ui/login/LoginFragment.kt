package com.cn.exams.ui.login

import android.view.LayoutInflater
import com.cn.exams.core.BaseFragment
import com.cn.exams.databinding.FragmentLoginBinding

class LoginFragment
    : BaseFragment<FragmentLoginBinding, LoginContract.Presenter>(), LoginContract.View {

    override val setupBinding: (LayoutInflater) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate
    override val setupPresenter: () -> LoginContract.Presenter
        get() = { LoginPresenter(this) }
    override val setupViewModel: (LoginContract.Presenter) -> Unit
        get() = binding::setPresenter

    override fun initUI() {
        configSwp()
    }

    private fun configSwp() {
        binding.swp.setOnRefreshListener {
            binding.swp.isRefreshing = false
        }
    }
}