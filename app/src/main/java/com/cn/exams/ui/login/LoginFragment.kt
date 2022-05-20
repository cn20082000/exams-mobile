package com.cn.exams.ui.login

import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import com.cn.exams.core.BaseFragment
import com.cn.exams.databinding.FragmentLoginBinding
import com.cn.exams.lib.data.ErrorEnum
import com.cn.exams.lib.data.message
import com.cn.exams.lib.mess.Mess

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
        configInput()
    }

    private fun configSwp() {
        binding.swp.setOnRefreshListener {
            binding.swp.isRefreshing = false
        }
    }

    private fun configInput() {
        binding.etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) requestLogin()
            true
        }
    }

    override fun requestLogin() {
        binding.swp.isRefreshing = true
        binding.btnLogin.isEnabled = false
        presenter.login(binding.etUsername.text.toString(), binding.etPassword.text.toString())
    }

    override fun loginSuccess() {
        binding.swp.isRefreshing = false
        binding.btnLogin.isEnabled = true
        Mess.success(requireActivity(), "Dang nhap thanh cong")
    }

    override fun loginFailed(error: ErrorEnum) {
        binding.swp.isRefreshing = false
        binding.btnLogin.isEnabled = true
        Mess.error(requireActivity(), error.message(resources))
    }
}