package com.dom.presentation.base.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.dom.domain.model.Data

interface BaseListAdapterFactory {
    fun <D: Data, L: ListAdapterListener, V: BaseListViewHolder<D, L>, T: ViewBinding>makeViewHolder(
        parent: ViewGroup,
        type: Data.Companion.ListDataType,
        bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> T
    ): V

    fun <L: ListAdapterListener>make(
        type: Data.Companion.ListDataType,
        listener : L
    ): BaseListAdapter<*, *, *>
}