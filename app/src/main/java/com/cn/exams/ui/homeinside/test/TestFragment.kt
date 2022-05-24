package com.cn.exams.ui.homeinside.test

import android.content.Intent
import android.view.LayoutInflater
import com.cn.exams.core.BaseFragment
import com.cn.exams.data.remote.response.Contest4JoinResponse
import com.cn.exams.databinding.FragmentTestBinding
import com.cn.exams.lib.data.ErrorEnum
import com.cn.exams.lib.data.message
import com.cn.exams.lib.mess.Mess
import com.cn.exams.ui.intest.InTestActivity

class TestFragment
    : BaseFragment<FragmentTestBinding, TestContract.Presenter>(), TestContract.View {

    override val setupBinding: (LayoutInflater) -> FragmentTestBinding
        get() = FragmentTestBinding::inflate
    override val setupPresenter: () -> TestContract.Presenter
        get() = { TestPresenter(this) }
    override val setupViewModel: (TestContract.Presenter) -> Unit
        get() = binding::setPresenter

    override fun initUI() {
        configToolbar()
        configSwp()
    }

    private fun configToolbar() {
        binding.tb.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun configSwp() {
        binding.swp.setOnRefreshListener { binding.swp.isRefreshing = false }
    }

    override fun requestJoin() {
        binding.swp.isRefreshing = true
        binding.btnStart.isEnabled = false
    }

    override fun joinSuccess(contest: Contest4JoinResponse) {
        binding.swp.isRefreshing = false
        binding.btnStart.isEnabled = true

        val intent = Intent(requireActivity(), InTestActivity::class.java)
        intent.putExtra(InTestActivity.ARGUMENT_CONTEST, contest)
        startActivity(intent)
    }

    override fun joinFailed(error: ErrorEnum) {
        binding.swp.isRefreshing = false
        binding.btnStart.isEnabled = true
        Mess.error(requireActivity(), error.message(resources))
    }
}