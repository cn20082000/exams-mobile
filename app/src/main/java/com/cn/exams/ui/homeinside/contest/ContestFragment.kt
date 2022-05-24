package com.cn.exams.ui.homeinside.contest

import android.view.LayoutInflater
import com.cn.exams.R
import com.cn.exams.core.BaseFragment
import com.cn.exams.databinding.FragmentContestBinding

class ContestFragment
    : BaseFragment<FragmentContestBinding, ContestContract.Presenter>(), ContestContract.View {

    override val setupBinding: (LayoutInflater) -> FragmentContestBinding
        get() = FragmentContestBinding::inflate
    override val setupPresenter: () -> ContestContract.Presenter
        get() = { ContestPresenter(this) }
    override val setupViewModel: (ContestContract.Presenter) -> Unit
        get() = binding::setPresenter

    override fun requestTestNow() {
        navigation.navigate(
            R.id.action_fragment_contest_to_fragment_test
        )
    }
}