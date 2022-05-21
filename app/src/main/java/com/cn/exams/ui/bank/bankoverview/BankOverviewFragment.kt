package com.cn.exams.ui.bank.bankoverview

import android.view.LayoutInflater
import com.cn.exams.R
import com.cn.exams.core.BaseFragment
import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.databinding.FragmentBankOverviewBinding
import com.cn.exams.lib.data.ErrorEnum
import com.cn.exams.lib.data.message
import com.cn.exams.lib.mess.Mess
import com.cn.exams.ui.bank.bankoverview.adapter.BankOverviewRecyclerAdapter

class BankOverviewFragment
    : BaseFragment<FragmentBankOverviewBinding, BankOverviewContract.Presenter>(), BankOverviewContract.View {

    override val setupBinding: (LayoutInflater) -> FragmentBankOverviewBinding
        get() = FragmentBankOverviewBinding::inflate
    override val setupPresenter: () -> BankOverviewContract.Presenter
        get() = { BankOverviewPresenter(this) }
    override val setupViewModel: (BankOverviewContract.Presenter) -> Unit
        get() = binding::setPresenter

    private var adapter: BankOverviewRecyclerAdapter? = null

    override fun initUI() {
        configSwp()
        configRcv()
    }

    override fun updateUI() {
        presenter.search(binding.etSearch.text.toString())
    }

    private fun configSwp() {
        binding.swp.setOnRefreshListener { updateUI() }
    }

    private fun configRcv() {
        adapter = BankOverviewRecyclerAdapter(mutableListOf())
        binding.rcv.adapter = adapter
    }

    override fun requestPersonalChange(isPublic: Boolean) {
        if (isPublic) {
            binding.swPersonal.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_public, 0, 0, 0)
            binding.swPersonal.text = getString(R.string._public)
            binding.etSearch.setText("")
        } else {
            binding.swPersonal.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_person, 0, 0, 0)
            binding.swPersonal.text = getString(R.string.personal)
            binding.etSearch.setText("")
        }
    }

    override fun requestSearch() {
        binding.swp.isRefreshing = true
    }

    override fun searchSuccess(list: List<BankOverviewResponse>) {
        binding.swp.isRefreshing = false
        adapter?.updateData(list.toMutableList())
    }

    override fun searchFailed(error: ErrorEnum) {
        binding.swp.isRefreshing = false
        Mess.error(requireActivity(), error.message(resources))
    }
}