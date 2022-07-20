package com.dom.presentation.base.delegate

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T: ViewBinding> Fragment.viewBiding(noinline viewBindingFactory: (View) -> T) = FragmentViewBindingDelegate(this, viewBindingFactory)

class FragmentViewBindingDelegate<T: ViewBinding>(
    private val fragment: Fragment,
    private val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<Fragment, T>{

    private var binding: T? = null
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        binding?.let { return it }

        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            binding = null
                            super.onDestroy(owner)
                        }
                    })
                }
                super.onCreate(owner)
            }
        })

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. Current lifecycle state is ${lifecycle.currentState}!")
        }

        return viewBindingFactory.invoke(thisRef.requireView()).also { this.binding = it }
    }

}