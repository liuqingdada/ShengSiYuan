package com.android.lib.uicommon.epoxy

import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.airbnb.epoxy.EpoxyModel
import com.android.lib.uicommon.R
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.util.concurrent.ConcurrentHashMap

abstract class ViewBindingKotlinModel<T : ViewBinding>(
    @LayoutRes private val layoutRes: Int
) : EpoxyModel<View>() {
    // Using reflection to get the static binding method.
    // Lazy so it's computed only once by instance, when the 1st ViewHolder is actually created.
    private val bindingMethod by lazy { getBindMethodFrom(this::class.java) }

    abstract fun T.bind()
    abstract fun T.unbind()
    open fun T.bind(payloads: MutableList<Any>) {}
    open fun T.bind(previouslyBoundModel: EpoxyModel<*>) {}

    override fun bind(view: View) {
        binding(view).bind()
    }

    override fun bind(view: View, payloads: MutableList<Any>) {
        super.bind(view, payloads)
        binding(view).bind(payloads)
    }

    override fun bind(view: View, previouslyBoundModel: EpoxyModel<*>) {
        super.bind(view, previouslyBoundModel)
        binding(view).bind(previouslyBoundModel)
    }

    override fun unbind(view: View) {
        super.unbind(view)
        binding(view).unbind()
    }

    override fun getDefaultLayout() = layoutRes

    @Suppress("UNCHECKED_CAST")
    private fun binding(view: View): T {
        var binding = view.getTag(R.id.epoxy_viewbinding) as? T
        if (binding == null) {
            binding = bindingMethod.invoke(null, view) as T
            view.setTag(R.id.epoxy_viewbinding, binding)
        }
        return binding
    }
}

// Static cache of a method pointer for each type of item used.
private val sBindingMethodByClass = ConcurrentHashMap<Class<*>, Method>()

@Suppress("UNCHECKED_CAST")
@Synchronized
private fun getBindMethodFrom(javaClass: Class<*>): Method =
    sBindingMethodByClass.getOrPut(javaClass) {
        val actualTypeOfThis = getSuperclassParameterizedType(javaClass)
        val viewBindingClass = actualTypeOfThis.actualTypeArguments[0] as Class<ViewBinding>
        viewBindingClass.getDeclaredMethod("bind", View::class.java)
            ?: error("The binder class ${javaClass.canonicalName} should have a method bind(View)")
    }

private fun getSuperclassParameterizedType(klass: Class<*>): ParameterizedType {
    val genericSuperclass = klass.genericSuperclass
    return (genericSuperclass as? ParameterizedType)
        ?: getSuperclassParameterizedType(genericSuperclass as Class<*>)
}
