package com.cn.exams.ui.contest.edit

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.View
import com.cn.exams.R
import com.cn.exams.common.Constant
import com.cn.exams.core.BaseFragment
import com.cn.exams.data.remote.response.BankResponse
import com.cn.exams.databinding.FragmentContestEditBinding
import com.cn.exams.lib.data.ErrorEnum
import com.cn.exams.lib.data.message
import com.cn.exams.lib.mess.Mess
import com.cn.exams.util.toCalendar
import java.text.ParseException
import java.util.*
import kotlin.math.min

class ContestEditFragment
    :BaseFragment<FragmentContestEditBinding, ContestEditContract.Presenter>(), ContestEditContract.View {

    override val setupBinding: (LayoutInflater) -> FragmentContestEditBinding
        get() = FragmentContestEditBinding::inflate
    override val setupPresenter: () -> ContestEditContract.Presenter
        get() = { ContestEditPresenter(this, mode, bankId) }
    override val setupViewModel: (ContestEditContract.Presenter) -> Unit
        get() = binding::setPresenter

    private val mode by lazy { arguments?.getInt(ARGUMENT_MODE) ?: MODE_ADD }
    private val bankId by lazy { arguments?.getLong(ARGUMENT_BANK_ID) ?: -1 }
    private var newestBank: BankResponse? = null

    override fun initUI() {
        configSwp()
        configToolbar()
        configInput()
    }

    override fun updateUI() {
        binding.swp.isRefreshing = true
        binding.btnAction.isEnabled = false
        presenter.getBankInfo(bankId)
    }

    private fun configSwp() {
        binding.swp.setOnRefreshListener { updateUI() }
    }

    private fun configToolbar() {
        binding.tb.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun configInput() {
        binding.tilStartAt.setEndIconOnClickListener {
            val startAt = run {
                try {
                    Constant.dateFormat.parse(binding.startAt ?: "").toCalendar()
                } catch (ex: ParseException) {
                    Calendar.getInstance()
                }
            }

            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    binding.startAt = getString(R.string.date_format, day, month + 1, year)
                },
                startAt.get(Calendar.YEAR),
                startAt.get(Calendar.MONTH),
                startAt.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        binding.tilEndAt.setEndIconOnClickListener {
            val endAt = run {
                try {
                    Constant.dateFormat.parse(binding.endAt ?: "").toCalendar()
                } catch (ex: ParseException) {
                    Calendar.getInstance()
                }
            }

            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    binding.endAt = getString(R.string.date_format, day, month + 1, year)
                },
                endAt.get(Calendar.YEAR),
                endAt.get(Calendar.MONTH),
                endAt.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    override fun getBankInfoSuccess(bank: BankResponse) {
        binding.swp.isRefreshing = false
        binding.btnAction.isEnabled = true
        binding.tvName.text = bank.name
        binding.tvDescription.text = bank.description
        binding.tvQuestionQuantity.text = getString(R.string.question_quantity_template, bank.questionQuantity)
        binding.tvUpdated.text = getString(
            R.string.updated_at_bank,
            Constant.dateFormat.format(bank.updatedAt),
            bank.owner.detail.name
        )
        newestBank = bank
    }

    override fun getBankInfoFailed(error: ErrorEnum) {
        binding.swp.isRefreshing = false
        Mess.error(requireActivity(), error.message(resources))
    }

    override fun requestInfoToggle(isChecked: Boolean) {
        if (isChecked) {
            binding.tvName.isSingleLine = false
            binding.tvDescription.visibility = View.VISIBLE
            binding.tvUpdated.visibility = View.VISIBLE
            binding.tvQuestionQuantity.visibility = View.VISIBLE
        } else {
            binding.tvName.isSingleLine = true
            binding.tvDescription.visibility = View.GONE
            binding.tvUpdated.visibility = View.GONE
            binding.tvQuestionQuantity.visibility = View.GONE
        }
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
        Mess.success(requireActivity(), getString(R.string.add_contest_success))
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

        const val ARGUMENT_BANK_ID = "arg_bank_id"
        const val ARGUMENT_MODE = "arg_mode"
    }
}