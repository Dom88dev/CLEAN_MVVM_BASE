package com.dom.presentation.screen.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dom.domain.model.Data
import com.dom.presentation.base.list.BaseListAdapter
import com.dom.presentation.base.list.ListAdapterListener
import com.dom.presentation.databinding.ListItemUserBinding

class UserListAdapter(listener: UserListAdapterListener) :
    BaseListAdapter<Data.User, UserListAdapter.UserListAdapterListener, UserListViewHolder>(listener) {

    interface UserListAdapterListener : ListAdapterListener {
        fun onClickUserItem(data: Data.User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder(
            ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}