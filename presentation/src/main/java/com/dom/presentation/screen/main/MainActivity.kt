package com.dom.presentation.screen.main

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withStateAtLeast
import androidx.recyclerview.widget.DiffUtil
import com.dom.domain.model.Data
import com.dom.presentation.base.BaseActivity
import com.dom.presentation.base.delegate.viewBinding
import com.dom.presentation.databinding.ActivityMainBinding
import com.dom.presentation.screen.detail.UserDetailActivity
import com.dom.presentation.screen.main.event.MainIntent
import com.dom.presentation.screen.main.event.MainSideEffect
import com.dom.presentation.screen.main.event.MainState
import com.dom.presentation.screen.main.list.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<MainState, MainSideEffect, MainViewModel, ActivityMainBinding>(),
    UserListAdapter.UserListAdapterListener {
    override val vm: MainViewModel by viewModels()
    override val vb: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    override fun initViews() {
        with(vb) {
            list.adapter = UserListAdapter(this@MainActivity)
        }
    }

    override fun render(state: MainState) {
        super.render(state)
        with(vb) {
            list.adapter?.also {
                (it as UserListAdapter).submitList(state.users)
            }
        }
    }

    override fun navigate(from: String) {
        when (from) {
            UserDetailActivity::class.java.name -> {
                vm.resetNavigation()
                startActivity(Intent(this, UserDetailActivity::class.java).apply {
                    putExtra("data", vm.targetUser)
                })
            }
        }
    }

    override fun onClickUserItem(data: Data.User) {
        lifecycleScope.launch {
            lifecycle.withStateAtLeast(Lifecycle.State.STARTED) {
                launch {
                    vm.intents.send(MainIntent.ClickUserItem(data))
                }
            }
        }
    }

    override fun <D : Data> provideDiffUtil(): DiffUtil.ItemCallback<D> =
        object : DiffUtil.ItemCallback<D>() {
            override fun areItemsTheSame(oldItem: D, newItem: D): Boolean {
                return if (oldItem is Data.User && newItem is Data.User) oldItem.id == newItem.id
                else false
            }

            override fun areContentsTheSame(oldItem: D, newItem: D): Boolean {
                val isSame = if (oldItem is Data.User && newItem is Data.User)
                    oldItem.userName == newItem.userName && oldItem.name == newItem.name && oldItem.company.name == newItem.company.name
                            && oldItem.phone == newItem.phone && oldItem.email == newItem.email
                else false
                return areItemsTheSame(oldItem, newItem) && isSame
            }
        }


}