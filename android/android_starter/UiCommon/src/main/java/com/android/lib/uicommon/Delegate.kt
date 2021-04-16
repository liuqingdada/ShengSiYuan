package com.android.lib.uicommon

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.android.common.utils.ApplicationUtils
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by cooper
 * 21-1-12.
 * Email: 1239604859@qq.com
 */
fun Fragment.applicationContext(): Application = ApplicationUtils.getApplication()

inline fun <reified T : Activity> Context.startActivity() {
    startActivity(Intent(this, T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    })
}

inline fun <reified T : Activity> Fragment.startActivity() {
    startActivity(Intent(requireContext(), T::class.java).apply {
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

inline fun <reified T : ViewBinding> View.viewBinding() =
    ViewBindingDelegate(T::class.java, this)

class ViewBindingDelegate<T : ViewBinding>(
    private val bindingClass: Class<T>,
    private val view: View,
) : ReadOnlyProperty<View, T> {
    private var binding: T? = null

    override fun getValue(thisRef: View, property: KProperty<*>): T {
        binding?.let { return it }

        @Suppress("UNCHECKED_CAST")
        binding = try {
            val inflateMethod = bindingClass.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java
            )
            inflateMethod.invoke(null, LayoutInflater.from(thisRef.context), thisRef)
        } catch (e: NoSuchMethodException) {
            val inflateMethod = bindingClass.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            )
            inflateMethod.invoke(null, LayoutInflater.from(thisRef.context), thisRef, true) as T
        } as T
        return binding!!
    }
}

inline fun <reified T : ViewBinding> RecyclerView.ViewHolder.viewBinding() =
    ViewHolderDelegate(T::class.java, this)

class ViewHolderDelegate<T : ViewBinding>(
    private val bindingClass: Class<T>,
    private val holder: RecyclerView.ViewHolder,
) : ReadOnlyProperty<RecyclerView.ViewHolder, T> {
    private var binding: T? = null

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: RecyclerView.ViewHolder, property: KProperty<*>): T {
        binding?.let { return it }

        val bindMethod = bindingClass.getMethod("bind", View::class.java)
        binding = bindMethod.invoke(null, thisRef.itemView) as T
        return binding!!
    }
}
