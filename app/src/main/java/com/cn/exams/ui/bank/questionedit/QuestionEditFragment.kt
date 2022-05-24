package com.cn.exams.ui.bank.questionedit

import android.view.LayoutInflater
import android.view.View
import com.cn.exams.R
import com.cn.exams.core.BaseFragment
import com.cn.exams.data.remote.response.QuestionResponse
import com.cn.exams.databinding.FragmentQuestionEditBinding
import com.cn.exams.lib.data.ErrorEnum
import com.cn.exams.lib.data.message
import com.cn.exams.lib.mess.Mess
import com.cn.exams.ui.bank.questionedit.adapter.AnswerEditRecyclerAdapter
import com.cn.exams.util.isNotNull

class QuestionEditFragment
    : BaseFragment<FragmentQuestionEditBinding, QuestionEditContract.Presenter>(),
    QuestionEditContract.View {

    override val setupBinding: (LayoutInflater) -> FragmentQuestionEditBinding
        get() = FragmentQuestionEditBinding::inflate
    override val setupPresenter: () -> QuestionEditContract.Presenter
        get() = { QuestionEditPresenter(this, mode, bankId, question) }
    override val setupViewModel: (QuestionEditContract.Presenter) -> Unit
        get() = binding::setPresenter

    private val mode by lazy { arguments?.getInt(ARGUMENT_MODE) ?: MODE_ADD }
    private val bankId by lazy { arguments?.getLong(ARGUMENT_BANK_ID) ?: -1 }
    private val question by lazy { arguments?.getParcelable<QuestionResponse>(ARGUMENT_QUESTION) }
    private var adapter: AnswerEditRecyclerAdapter? = null

    override fun initUI() {
        configToolbar()
        configSwp()
        configRadio()
        configMode()
    }

    private fun configToolbar() {
        binding.tb.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun configSwp() {
        binding.swp.setOnRefreshListener { binding.swp.isRefreshing = false }
    }

    private fun configRcv() {
        adapter = AnswerEditRecyclerAdapter(mutableListOf())
        binding.rcv.adapter = adapter
    }

    private fun configRadio() {
        binding.rbCorrect1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.rbCorrect2.isChecked = false
                binding.rbCorrect3.isChecked = false
                binding.rbCorrect4.isChecked = false
            }
        }
        binding.rbCorrect2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.rbCorrect1.isChecked = false
                binding.rbCorrect3.isChecked = false
                binding.rbCorrect4.isChecked = false
            }
        }
        binding.rbCorrect3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.rbCorrect2.isChecked = false
                binding.rbCorrect1.isChecked = false
                binding.rbCorrect4.isChecked = false
            }
        }
        binding.rbCorrect4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.rbCorrect2.isChecked = false
                binding.rbCorrect3.isChecked = false
                binding.rbCorrect1.isChecked = false
            }
        }
    }

    private fun configMode() {
        if (mode == MODE_ADD) {
            binding.tb.title = getString(R.string.new_question)
            binding.btnAction.text = getString(R.string.add_new)
        } else {
            if (mode == MODE_EDIT) {
                binding.tb.title = getString(R.string.update_question)
                binding.btnAction.text = getString(R.string.update)
            } else {
                binding.tb.title = ""
                binding.btnAction.visibility = View.GONE
                binding.etContent.isEnabled = false
                binding.etExplanation.isEnabled = false
                binding.rbCorrect1.isEnabled = false
                binding.rbCorrect2.isEnabled = false
                binding.rbCorrect3.isEnabled = false
                binding.rbCorrect4.isEnabled = false
                binding.etAnswer1.isEnabled = false
                binding.etAnswer2.isEnabled = false
                binding.etAnswer3.isEnabled = false
                binding.etAnswer4.isEnabled = false
            }

            binding.content = question?.content ?: ""
            binding.explanation = question?.explanation ?: ""
            binding.answer1 = getAns(0)
            binding.answer2 = getAns(1)
            binding.answer3 = getAns(2)
            binding.answer4 = getAns(3)
            when (question?.correctAnswer) {
                0 -> binding.rbCorrect1.isChecked = true
                1 -> binding.rbCorrect2.isChecked = true
                2 -> binding.rbCorrect3.isChecked = true
                3 -> binding.rbCorrect4.isChecked = true
            }
        }
    }

    private fun getAns(at: Int): String {
        question?.let {
            if (at >= 0 && at < it.answer.size) {
                return it.answer[at].content
            }
        }
        return ""
    }

    override fun requestAddAnswer() {
        adapter?.addAnswer()
    }

    override fun requestAnswerList(): List<String> {
        return adapter?.getAnswer()?.toMutableList() ?: mutableListOf()
    }

    override fun requestCorrectAnswer(): Int {
        if (binding.rbCorrect1.isChecked) return 0
        if (binding.rbCorrect2.isChecked) return 1
        if (binding.rbCorrect3.isChecked) return 2
        if (binding.rbCorrect4.isChecked) return 3
        return -1
    }

    override fun requestAction() {
        binding.swp.isRefreshing = true
        binding.btnAction.isEnabled = false
    }

    override fun actionSuccess() {
        binding.swp.isRefreshing = false
        binding.btnAction.isEnabled = true
        Mess.success(requireActivity(), getString(
            if (mode == MODE_ADD) R.string.add_new_success
            else R.string.update_success
        ))
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
        const val MODE_VIEW = 3

        const val ARGUMENT_MODE = "arg_mode"
        const val ARGUMENT_QUESTION = "arg_question"
        const val ARGUMENT_BANK_ID = "arg_bank_id"
    }
}