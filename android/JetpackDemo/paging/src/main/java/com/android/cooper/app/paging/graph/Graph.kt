package com.android.cooper.app.paging.graph

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.View
import com.android.common.utils.ApplicationUtils


/**
 * Created by cooper
 * 20-12-14.
 * Email: 1239604859@qq.com
 */
abstract class Graph @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    companion object {
        fun dp2px(dipValue: Float): Float {
            val context = ApplicationUtils.getApplication()
            val scale = context.resources.displayMetrics.density
            return dipValue * scale + 0.5f
        }

        fun px2dp(pxValue: Float): Float {
            val context = ApplicationUtils.getApplication()
            val scale = context.resources.displayMetrics.density
            return pxValue / scale + 0.5f
        }

        fun px2sp(pxValue: Float): Float {
            val context = ApplicationUtils.getApplication()
            val fontScale = context.resources.displayMetrics.scaledDensity
            return pxValue / fontScale + 0.5f
        }

        fun sp2px(spValue: Float): Float {
            val context = ApplicationUtils.getApplication()
            val fontScale = context.resources.displayMetrics.scaledDensity
            return spValue * fontScale + 0.5f
        }

        fun big(bitmap: Bitmap): Bitmap {
            val matrix = Matrix()
            matrix.postScale(1.5f, 1.5f) // 长和宽放大缩小的比例
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        }

        fun small(bitmap: Bitmap): Bitmap {
            val matrix = Matrix()
            matrix.postScale(0.8f, 0.8f) // 长和宽放大缩小的比例
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var hms = heightMeasureSpec
        val wSpectMode = MeasureSpec.getMode(widthMeasureSpec)
        val wSpectSize = MeasureSpec.getSize(widthMeasureSpec)
        if (hms == 0) { // ScrollView
            hms = MeasureSpec.makeMeasureSpec(wSpectSize, MeasureSpec.AT_MOST)
        }
        val hSpectMode = MeasureSpec.getMode(hms)
        val hSpectSize = MeasureSpec.getSize(hms)
        if (wSpectMode == MeasureSpec.AT_MOST && hSpectMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(maxWidth(), maxHeight())
        } else if (wSpectMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(maxWidth(), hSpectSize)
        } else if (hSpectMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(wSpectSize, hSpectSize)
        }
    }

    abstract fun maxWidth(): Int

    abstract fun maxHeight(): Int
}