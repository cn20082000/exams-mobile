package com.cn.exams.core

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder<T>>(
    protected val items: MutableList<T>
) : RecyclerView.Adapter<VH>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bindData(items[position], position)

    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean

    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    fun updateData(newItems: MutableList<T>) {
        val diffCallback = DiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    private inner class DiffCallback(
        private val oldItems: MutableList<T>,
        private val newItems: MutableList<T>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            areItemsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            areContentsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])
    }
}