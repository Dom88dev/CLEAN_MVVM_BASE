package com.dom.presentation.base.list

import androidx.recyclerview.widget.ListAdapter
import com.dom.domain.model.Data

abstract class BaseListAdapter<D: Data, L: ListAdapterListener, V: BaseListViewHolder<D, L>>(
    private val listener: L,
) : ListAdapter<D, V>(listener.provideDiffUtil()){

    override fun onBindViewHolder(holder: V, position: Int) {
        val data = getItem(position)
        data?.also {
            with(holder) {
                bindData(it)
                bindViews(it, listener)
            }
        }
    }
}