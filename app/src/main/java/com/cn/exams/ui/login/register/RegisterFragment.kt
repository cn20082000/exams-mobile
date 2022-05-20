package com.cn.exams.ui.login.register

import android.app.DatePickerDialog
import android.view.LayoutInflater
import com.cn.exams.R
import com.cn.exams.common.Constant
import com.cn.exams.core.BaseFragment
import com.cn.exams.databinding.FragmentRegisterBinding
import com.cn.exams.lib.data.ErrorEnum
import com.cn.exams.lib.data.message
import com.cn.exams.lib.mess.Mess
import com.cn.exams.util.toCalendar
import java.text.ParseException
import java.util.*

class RegisterFragment
    : BaseFragment<FragmentRegisterBinding, RegisterContract.Presenter>(), RegisterContract.View {

    override val setupBinding: (LayoutInflater) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate
    override val setupPresenter: () -> RegisterContract.Presenter
        get() = { RegisterPresenter(this) }
    override val setupViewModel: (RegisterContract.Presenter) -> Unit
        get() = binding::setPresenter

    override fun initUI() {
        configToolbar()
        configSwp()
        configInput()
    }

    private fun configToolbar() {
        binding.tb.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun configSwp() {
        binding.swp.setOnRefreshListener { binding.swp.isRefreshing = false }
    }

    private fun configInput() {
        binding.tilBirth.setEndIconOnClickListener {
            val birth = run {
                try {
                    Constant.dateFormat.parse(binding.etBirth.text.toString()).toCalendar()
                } catch (ex: ParseException) {
                    Calendar.getInstance()
                }
            }

            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    binding.etBirth.setText(getString(R.string.date_format, day, month + 1, year))
                },
                birth.get(Calendar.YEAR),
                birth.get(Calendar.MONTH),
                birth.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    override fun requestRegister() {
        binding.swp.isRefreshing = true
        binding.btnRegister.isEnabled = false
        presenter.register(
            binding.etName.text.toString(),
            binding.etBirth.text.toString(),
            binding.etAddress.text.toString(),
            binding.etTel.text.toString(),
            binding.etUsername.text.toString(),
            binding.etPassword.text.toString(),
            binding.etCfPassword.text.toString(),
        )
    }

    override fun registerSuccess() {
        binding.swp.isRefreshing = false
        binding.btnRegister.isEnabled = true
        Mess.success(requireActivity(), getString(R.string.register_success))
        activity?.onBackPressed()
    }

    override fun registerFailed(error: ErrorEnum) {
        binding.swp.isRefreshing = false
        binding.btnRegister.isEnabled = true
        Mess.error(requireActivity(), error.message(resources))
    }
}