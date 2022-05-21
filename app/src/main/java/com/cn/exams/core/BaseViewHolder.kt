package com.cn.exams.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {

    abstract fun bindData(data: T, index: Int)
}