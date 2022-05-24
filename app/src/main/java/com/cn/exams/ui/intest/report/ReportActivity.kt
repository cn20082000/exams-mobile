package com.cn.exams.ui.intest.report

import android.view.LayoutInflater
import com.cn.exams.R
import com.cn.exams.core.BaseActivity
import com.cn.exams.data.remote.response.ReportResponse
import com.cn.exams.databinding.ActivityReportBinding

class ReportActivity
    : BaseActivity<ActivityReportBinding, ReportContract.Presenter>(), ReportContract.View {

    override val setupBinding: (LayoutInflater) -> ActivityReportBinding
        get() = ActivityReportBinding::inflate
    override val setupPresenter: () -> ReportContract.Presenter
        get() = { ReportPresenter(this) }
    override val setupViewModel: (ReportContract.Presenter) -> Unit
        get() = binding::setPresenter

    val report by lazy { intent.getParcelableExtra<ReportResponse>(ARGUMENT_REPORT) }

    override fun initUI() {
        configToolbar()
        configData()
    }

    private fun configData() {
        binding.tvScore.text = getString(R.string.score_template, report?.score ?: 0, report?.contest?.questionQuantity ?: 0)
        val endTime = report?.updatedAt?.time ?: 0
        val startTime = report?.createdAt?.time ?: 0
        var time = endTime - startTime
        val hour: Long = time / 36000000
        time -= hour * 36000000
        val minute: Long = time / 60000
        time -= minute * 60000
        binding.tvTime.text = getString(R.string.test_time_template, hour, minute, (time / 1000).toInt())
    }

    private fun configToolbar() {
        binding.tb.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun requestBack() {
        onBackPressed()
    }

    companion object {
        const val ARGUMENT_REPORT = "arg_report"
    }
}