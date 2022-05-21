package com.cn.exams.ui.bank.edit

import android.view.LayoutInflater
import com.cn.exams.R
import com.cn.exams.core.BaseFragment
import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.data.remote.response.BankResponse
import com.cn.exams.databinding.FragmentBankEditBinding
import com.cn.exams.lib.data.ErrorEnum
import com.cn.exams.lib.data.message
import com.cn.exams.lib.mess.Mess
import com.cn.exams.util.enumi.BankScopeEnum

class BankEditFragment
    : BaseFragment<FragmentBankEditBinding, BankEditContract.Presenter>(), BankEditContract.View {

    override val setupBinding: (LayoutInflater) -> FragmentBankEditBinding
        get() = FragmentBankEditBinding::inflate
    override val setupPresenter: () -> BankEditContract.Presenter
        get() = { BankEditPresenter(this, mode, bank) }
    override val setupViewModel: (BankEditContract.Presenter) -> Unit
        get() = binding::setPresenter

    private val mode by lazy { arguments?.getInt(ARGUMENT_MODE) ?: MODE_ADD }
    private val bank by lazy { arguments?.getParcelable<BankResponse>(ARGUMENT_BANK) }

    override fun initUI() {
        configToolbar()
        configMode()
        configSwp()
    }

    private fun configToolbar() {
        binding.tb.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun configMode() {
        if (mode == MODE_ADD) {
            binding.tb.title = getString(R.string.new_bank)
            binding.btnAction.text = getString(R.string.add_new)
        } else if (mode == MODE_EDIT) {
            binding.tb.title = getString(R.string.update_bank)
            binding.swPersonal.isEnabled = false
            binding.btnAction.text = getString(R.string.update)
            binding.name = bank?.name ?: ""
            binding.description = bank?.description ?: ""
            binding.isPublic = bank?.scope != BankScopeEnum.PERSONAL
        }
    }

    private fun configSwp() {
        binding.swp.setOnRefreshListener { binding.swp.isRefreshing = false }
    }

    override fun requestPersonalChange(isPublic: Boolean) {
        if (isPublic) {
            binding.swPersonal.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_public, 0, 0, 0)
            binding.swPersonal.text = getString(R.string._public)
        } else {
            binding.swPersonal.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_lock, 0, 0, 0)
            binding.swPersonal.text = getString(R.string._private)
        }
    }

    override fun requestAction() {
        binding.swp.isRefreshing = true
        binding.btnAction.isEnabled = false
    }

    override fun actionSuccess() {
        binding.swp.isRefreshing = false
        binding.btnAction.isEnabled = true
        Mess.success(requireActivity(), getString(R.string.add_new_success))
        activity?.onBackPressed()
    }

    override fun actionFailed(error: ErrorEnum) {
        binding.swp.isRefreshing = false
        binding.btnAction.isEnabled = true
        Mess.error(requireActivity(), error.message(resources))
    }

    companion object {
        const val MODE_ADD = 1
        const val MODE_EDIT = 2

        const val ARGUMENT_MODE = "arg_mode"
        const val ARGUMENT_BANK = "arg_bank"
    }
}