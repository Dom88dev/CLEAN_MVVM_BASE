package com.dom.presentation.screen.detail

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withStateAtLeast
import com.dom.domain.model.Data
import com.dom.presentation.base.BaseActivity
import com.dom.presentation.base.delegate.viewBinding
import com.dom.presentation.databinding.ActivityUserDetailBinding
import com.dom.presentation.screen.detail.event.UserDetailIntent
import com.dom.presentation.screen.detail.event.UserDetailSideEffect
import com.dom.presentation.screen.detail.event.UserDetailState
import com.dom.presentation.util.Extensions.serializableExtra
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailActivity: BaseActivity<UserDetailState, UserDetailSideEffect, UserDetailViewModel, ActivityUserDetailBinding>() {
    override val vm: UserDetailViewModel by viewModels()
    override val vb: ActivityUserDetailBinding by viewBinding(ActivityUserDetailBinding::inflate)

    override fun initViews() {
        with(vb) {
            intent.serializableExtra<Data.User>("data")?.also {
                lifecycleScope.launch {
                    lifecycle.withStateAtLeast(Lifecycle.State.STARTED) {
                        launch {
                            vm.intents.send(UserDetailIntent.SetUserData(it))
                        }
                    }
                }

            }
        }
    }

    override fun navigate(from: String) {}

    @SuppressLint("SetTextI18n")
    override fun render(state: UserDetailState) {
        super.render(state)
        state.user?.also {
            with(vb) {
                userName.text = it.userName
                name.text = it.name
                bs.text = it.company.bs
                catchPhrase.text = it.company.catchPhrase
                company.text = it.company.name
                address.text = it.address
                phoneNEmail.text = "${it.phone} / ${it.email}"
                web.text = it.webSite
            }
        }
    }
}