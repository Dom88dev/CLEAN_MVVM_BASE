package com.dom.presentation.screen.main.list

import android.annotation.SuppressLint
import com.dom.domain.model.Data
import com.dom.presentation.base.list.BaseListViewHolder
import com.dom.presentation.databinding.ListItemUserBinding

class UserListViewHolder(private val binding: ListItemUserBinding): BaseListViewHolder<Data.User, UserListAdapter.UserListAdapterListener>(binding) {
    override fun reset() {
        binding.root.setOnClickListener(null)
    }

    @SuppressLint("SetTextI18n")
    override fun bindViews(data: Data.User, listener: UserListAdapter.UserListAdapterListener) {
        with(binding) {
            userName.text = data.userName
            name.text = data.name
            company.text = data.company.name
            address.text = data.address
            phoneNEmail.text = "${data.phone} / ${data.email}"
            root.setOnClickListener {
                listener.onClickUserItem(data)
            }
        }
    }
}