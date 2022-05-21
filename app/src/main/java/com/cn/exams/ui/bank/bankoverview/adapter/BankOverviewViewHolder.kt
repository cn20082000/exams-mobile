package com.cn.exams.ui.bank.bankoverview.adapter

import android.content.Context
import com.cn.exams.R
import com.cn.exams.common.App
import com.cn.exams.common.Constant
import com.cn.exams.core.BaseViewHolder
import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.databinding.ItemBankOverviewBinding
import com.cn.exams.util.enumi.BankScopeEnum

class BankOverviewViewHolder(
    private val binding: ItemBankOverviewBinding,
    private val context: Context,
    private val onItemClick: (BankOverviewResponse) -> Unit
) : BaseViewHolder<BankOverviewResponse>(binding.root) {

    override fun bindData(data: BankOverviewResponse, index: Int) {
        binding.root.setOnClickListener { onItemClick(data) }

        binding.tvName.text = data.name

        if (data.scope == BankScopeEnum.PERSONAL) {
            binding.ivScope.setImageResource(R.drawable.ic_lock)
        } else {
            if (data.owner.id == App.user?.id) {
                binding.ivScope.setImageResource(R.drawable.ic_edit)
            } else {
                binding.ivScope.setImageResource(R.drawable.ic_eye)
            }
        }

        binding.tvUpdated.text = context.resources.getString(
            R.string.updated_at_bank,
            Constant.dateFormat.format(data.updatedAt),
            data.owner.detail.name
        )
    }
}