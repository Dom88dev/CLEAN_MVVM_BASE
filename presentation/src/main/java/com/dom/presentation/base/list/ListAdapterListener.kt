package com.dom.presentation.base.list

import androidx.recyclerview.widget.DiffUtil
import com.dom.domain.model.Data

interface ListAdapterListener {
    fun <D: Data>provideDiffUtil(): DiffUtil.ItemCallback<D>
}
