package com.android.cooper.app.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by cooper
 * 21-1-12.
 * Email: 1239604859@qq.com
 */
//inline fun <reified T : Activity> Activity.startActivity() {
//    startActivity(Intent(this, T::class.java).apply {
//        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//    })
//}

inline fun <reified T : Activity> Context.startActivity() {
    startActivity(Intent(this, T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    })
}

inline fun <reified T : ViewBinding> Activity.viewBinding() =
    ActivityViewBindingDelegate(T::class.java)

class ActivityViewBindingDelegate<T : ViewBinding>(
    private val bindingClass: Class<T>,
) : ReadOnlyProperty<Activity, T> {
    private var binding: T? = null

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        binding?.let { return it }

        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)
        binding = inflateMethod.invoke(null, thisRef.layoutInflater) as T
        thisRef.setContentView(binding!!.root)
        return binding!!
    }
}

inline fun <reified T : ViewBinding> Fragment.viewBinding() =
    FragmentViewBindingDelegate(this, T::class.java)

class FragmentViewBindingDelegate<T : ViewBinding>(
    fragment: Fragment,
    private val bindingClass: Class<T>,
) : ReadOnlyProperty<Fragment, T> {
    private val clearBindingHandler by lazy { Handler(Looper.getMainLooper()) }
    private var binding: T? = null

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) {
            it.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroy() {
                    clearBindingHandler.post { binding = null }
                }
            })
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        binding?.let { return it }

        val bindMethod = bindingClass.getMethod(
            "bind",
            View::class.java,
        )
        val lifecycle = thisRef.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. View lifecycle is ${lifecycle.currentState}")
        }
        binding = bindMethod.invoke(null, thisRef.requireView()) as T
        return binding!!
    }
}