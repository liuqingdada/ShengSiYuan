package com.android.cooper.app.paging.graph

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.android.cooper.app.paging.R

/**
 * Created by cooper
 * 20-12-14.
 * Email: 1239604859@qq.com
 *
 * [Paint.measureText]
 * [Paint.getTextBounds]
 */
class DrawingFont2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Graph(context, attrs, defStyleAttr) {
    private val paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.YELLOW)
        val positions = floatArrayOf(
            80F, 100F,
            80F, 200F,
            80F, 300F
        )
        canvas?.drawPosText("自定义", positions, paint)
    }

    override fun maxWidth(): Int = 1500

    override fun maxHeight(): Int = 500

    init {
        paint.color = Color.DKGRAY
        paint.strokeWidth = 3F
        paint.isAntiAlias = true
        paint.textSize = sp2px(30F)
    }
}