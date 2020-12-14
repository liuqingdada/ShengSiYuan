package com.android.cooper.app.paging.graph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by cooper
 * 20-12-14.
 * Email: 1239604859@qq.com
 */
class DrawingBasics @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.DKGRAY)

        /**
         * 画笔用法
         */
        paint.isAntiAlias = true

        val r = 50F
        paint.strokeWidth = 50F

        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        canvas?.drawCircle(100F, 100F, r, paint)

        paint.style = Paint.Style.FILL
        paint.color = Color.GREEN
        canvas?.drawCircle(300F, 100F, r, paint)

        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.BLUE
        canvas?.drawCircle(500F, 100F, r, paint)


        /**
         * 画布用法
         * Canvas 提供了三个设置背景的方法。
         * drawColor(int color)
         * drawARGB(int a, int r, int g, int b)
         * drawRGB(int r, int g, int b)
         * 需要注意的是，设置画布背景要在其他图形绘制前设置，否则设置好的背景色会覆盖原有的图形
         */

        paint.strokeWidth = 10F
        canvas?.drawLine(100F, 300F, 200F, 300F, paint)
        paint.strokeWidth = 20F
        canvas?.drawLine(300F, 300F, 400F, 300F, paint)

        paint.strokeWidth = 10F
        canvas?.drawPoint(100F, 500F, paint)
        paint.strokeWidth = 20F
        canvas?.drawPoint(300F, 500F, paint)
    }
}