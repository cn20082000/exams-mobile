package com.cn.exams.ui.bank.bankoverview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cn.exams.core.BaseRecyclerAdapter
import com.cn.exams.data.remote.response.BankOverviewResponse
import com.cn.exams.databinding.ItemBankOverviewBinding

class BankOverviewRecyclerAdapter(
    items: MutableList<BankOverviewResponse>,
    private val onItemClick: (BankOverviewResponse) -> Unit
) : BaseRecyclerAdapter<BankOverviewResponse, BankOverviewViewHolder>(items) {

    override fun areContentsTheSame(
        oldItem: BankOverviewResponse,
        newItem: BankOverviewResponse
    ): Boolean = oldItem == newItem

    override fun areItemsTheSame(
        oldItem: BankOverviewResponse,
        newItem: BankOverviewResponse
    ): Boolean = oldItem.id == newItem.id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BankOverviewViewHolder(
            ItemBankOverviewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context,
            onItemClick
        )
}