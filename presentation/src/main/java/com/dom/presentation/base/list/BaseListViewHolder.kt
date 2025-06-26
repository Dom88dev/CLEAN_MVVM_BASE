package com.dom.presentation.base.list

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.dom.domain.model.Data

abstract class BaseListViewHolder<D: Data, L: ListAdapterListener>(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun reset()

    open fun bindData(data: D) {
        reset()
    }

    abstract fun bindViews(data: D, listener: L)
}