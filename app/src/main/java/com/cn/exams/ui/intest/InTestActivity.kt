package com.cn.exams.ui.intest

import android.content.Intent
import android.view.LayoutInflater
import com.cn.exams.core.BaseActivity
import com.cn.exams.data.remote.response.Contest4JoinResponse
import com.cn.exams.data.remote.response.ReportResponse
import com.cn.exams.databinding.ActivityInTestBinding
import com.cn.exams.lib.data.ErrorEnum
import com.cn.exams.lib.data.message
import com.cn.exams.lib.mess.Mess
import com.cn.exams.ui.intest.adapter.QuestionTestRecyclerAdapter
import com.cn.exams.ui.intest.report.ReportActivity

class InTestActivity
    : BaseActivity<ActivityInTestBinding, InTestContract.Presenter>(), InTestContract.View {

    override val setupBinding: (LayoutInflater) -> ActivityInTestBinding
        get() = ActivityInTestBinding::inflate
    override val setupPresenter: () -> InTestContract.Presenter
        get() = { InTestPresenter(this) }
    override val setupViewModel: (InTestContract.Presenter) -> Unit
        get() = binding::setPresenter

    private val contest by lazy { intent.getParcelableExtra<Contest4JoinResponse>(ARGUMENT_CONTEST) }
    private var adapter: QuestionTestRecyclerAdapter? = null

    override fun initUI() {
        configToolbar()
        configRcv()
        configSwp()
    }

    private fun configToolbar() {
        binding.tb.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.tb.title = contest?.name ?: ""
    }

    private fun configRcv() {
        adapter = QuestionTestRecyclerAdapter(contest?.questions?.toMutableList() ?: mutableListOf())
        binding.rcv.adapter = adapter
    }

    private fun configSwp() {
        binding.swp.setOnRefreshListener { binding.swp.isRefreshing = false }
    }

    override fun getAnswerList(): List<Int> {
        return adapter?.getAnswerList()?.toList() ?: emptyList()
    }

    override fun requestSubmit() {
        binding.swp.isRefreshing = true
        binding.btnSubmit.isEnabled = false
    }

    override fun submitSuccess(report: ReportResponse) {
        binding.swp.isRefreshing = false
        binding.btnSubmit.isEnabled = true

        val intent = Intent(this, ReportActivity::class.java)
        intent.putExtra(ReportActivity.ARGUMENT_REPORT, report)
        startActivity(intent)
        finish()
    }

    override fun submitFailed(error: ErrorEnum) {
        binding.swp.isRefreshing = false
        binding.btnSubmit.isEnabled = true
        Mess.error(this, error.message(resources))
    }

    companion object {
        const val ARGUMENT_CONTEST = "arg_contest"
    }
}