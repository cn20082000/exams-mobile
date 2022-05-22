package com.cn.exams.ui.bank.questionlist

import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import com.cn.exams.R
import com.cn.exams.common.App
import com.cn.exams.common.Constant
import com.cn.exams.core.BaseFragment
import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.data.remote.response.BankResponse
import com.cn.exams.data.remote.response.QuestionResponse
import com.cn.exams.databinding.FragmentQuestionListBinding
import com.cn.exams.lib.data.ErrorEnum
import com.cn.exams.lib.data.message
import com.cn.exams.lib.mess.Mess
import com.cn.exams.lib.task.TaskModule
import com.cn.exams.ui.bank.edit.BankEditFragment
import com.cn.exams.ui.bank.questionedit.QuestionEditFragment
import com.cn.exams.ui.bank.questionlist.adapter.QuestionRecyclerAdapter

class QuestionListFragment
    : BaseFragment<FragmentQuestionListBinding, QuestionListContract.Presenter>(),
    QuestionListContract.View {

    override val setupBinding: (LayoutInflater) -> FragmentQuestionListBinding
        get() = FragmentQuestionListBinding::inflate
    override val setupPresenter: () -> QuestionListContract.Presenter
        get() = { QuestionListPresenter(this, oldBank.id) }
    override val setupViewModel: (QuestionListContract.Presenter) -> Unit
        get() = binding::setPresenter

    private val oldBank by lazy { arguments?.getParcelable<BankOverviewResponse>(ARGUMENT_BANK)!! }
    private var task: TaskModule? = null
    private var newestBank: BankResponse? = null
    private var adapter: QuestionRecyclerAdapter? = null

    override fun initUI() {
        configToolbar()
        configSwp()
        configRcv()
    }

    override fun updateUI() {
        binding.swp.isRefreshing = true
        task = TaskModule(mutableListOf(TASK_INFO, TASK_SEARCH)) {
            binding.swp.isRefreshing = false
        }
        presenter.getBankInfo()
        presenter.searchQuestion(binding.etSearch.text.toString())
    }

    private fun configToolbar() {
        binding.tb.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun configSwp() {
        binding.swp.setOnRefreshListener { updateUI() }
    }

    private fun configRcv() {
        adapter = QuestionRecyclerAdapter(mutableListOf()) {
            navigation.navigate(
                R.id.action_fragment_question_list_to_fragment_question_edit,
                bundleOf(
                    QuestionEditFragment.ARGUMENT_MODE to if (newestBank?.owner?.id == App.user?.id) QuestionEditFragment.MODE_EDIT else QuestionEditFragment.MODE_VIEW,
                    QuestionEditFragment.ARGUMENT_QUESTION to it
                )
            )
        }
        binding.rcv.adapter = adapter
    }

    override fun requestInfoToggle(isChecked: Boolean) {
        if (isChecked) {
            binding.tvName.isSingleLine = false
            binding.tvDescription.visibility = View.VISIBLE
            binding.tvUpdated.visibility = View.VISIBLE
        } else {
            binding.tvName.isSingleLine = true
            binding.tvDescription.visibility = View.GONE
            binding.tvUpdated.visibility = View.GONE
        }
    }

    override fun getBankInfoSuccess(bank: BankResponse) {
        task?.markAsSuccess(TASK_INFO)
        newestBank = bank

        binding.tvName.text = bank.name
        binding.tvDescription.text = bank.description
        binding.tvUpdated.text = getString(
            R.string.updated_at_bank,
            Constant.dateFormat.format(bank.updatedAt),
            bank.owner.detail.name
        )
        if (App.user?.id == bank.owner.id) {
            binding.btnEdit.visibility = View.VISIBLE
            binding.btnAdd.visibility = View.VISIBLE
        } else {
            binding.btnEdit.visibility = View.GONE
            binding.btnAdd.visibility = View.GONE
        }
    }

    override fun getBankInfoFailed(error: ErrorEnum) {
        task?.markAsSuccess(TASK_INFO)
        Mess.error(requireActivity(), error.message(resources))
    }

    override fun searchQuestionSuccess(list: List<QuestionResponse>) {
        task?.markAsSuccess(TASK_SEARCH)
        adapter?.updateData(list.toMutableList())
    }

    override fun searchQuestionFailed(error: ErrorEnum) {
        task?.markAsSuccess(TASK_SEARCH)
        Mess.error(requireActivity(), error.message(resources))
    }

    override fun requestEditBank() {
        newestBank?.let {
            navigation.navigate(
                R.id.action_fragment_question_list_to_fragment_bank_edit,
                bundleOf(
                    BankEditFragment.ARGUMENT_MODE to BankEditFragment.MODE_EDIT,
                    BankEditFragment.ARGUMENT_BANK to it
                )
            )
        }
    }

    override fun requestAddQuestion() {
        navigation.navigate(
            R.id.action_fragment_question_list_to_fragment_question_edit,
            bundleOf(
                QuestionEditFragment.ARGUMENT_MODE to QuestionEditFragment.MODE_ADD,
                QuestionEditFragment.ARGUMENT_BANK_ID to oldBank.id
            )
        )
    }

    companion object {
        const val ARGUMENT_BANK = "arg_bank"

        const val TASK_INFO = 1
        const val TASK_SEARCH = 2
    }
}