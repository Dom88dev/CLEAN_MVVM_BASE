package com.dom.presentation.base.delegate

import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : ViewBinding> viewBinding(noinline bindingInflater: (LayoutInflater) -> T) = ActivityViewBindingDelegate(bindingInflater)

class ActivityViewBindingDelegate<T: ViewBinding>(
    private val viewBindingFactory: (LayoutInflater) -> T
    ) : ReadOnlyProperty<ComponentActivity, T> {

    private var binding: T? = null

    override fun getValue(thisRef: ComponentActivity, property: KProperty<*>): T {
        binding?.let { return it }

        if (!thisRef.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. Current lifecycle state is ${thisRef.lifecycle.currentState}")
        }

        val invokeLayout = viewBindingFactory.invoke(thisRef.layoutInflater)

        thisRef.setContentView(invokeLayout.root)

        return invokeLayout.also { this.binding = it }
    }

}