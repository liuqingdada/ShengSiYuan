package com.android.cooper.app.paging.graph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.annotation.ColorInt

/**
 * Created by cooper
 * 20-12-17.
 * Email: 1239604859@qq.com
 *
 * 使用 Canvas 的绘制方法有下面三个需要注意的点
 * 1. 生成新图层
 *    每次调用绘制方法 drawXXX 时，都会产生一个新的 Canvas 透明图层
 * 2. 操作不可逆
 *    调用了绘制方法前，平移和旋转等函数对 Canvas 进行了操作，那么这个操作是不可逆的，
 *    每次产生的画布的最新位置都是这些操作后的位置
 * 3. 超出不显示
 *    在 Canvas 图层与屏幕合成时，超出屏幕范围的图像是不会显示出来的
 */
class DrawingCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Graph(context, attrs, defStyleAttr) {
    private val paint = Paint()
    private val rect = Rect()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawRect(canvas, Color.GREEN)
        canvas?.translate(350F, 350F)
        drawRect(canvas, Color.RED)

        clipTest(canvas)
    }

    private fun drawRect(canvas: Canvas?, @ColorInt color: Int) {
        paint.color = color
        rect.set(50, 50, 300, 300)
        canvas?.drawRect(rect, paint)
    }

    private fun clipTest(canvas: Canvas?) {
        rect.set(50, 50, 100, 100)
        canvas?.clipRect(rect)
        canvas?.drawColor(Color.GREEN)
    }

    override fun maxWidth(): Int = 1000

    override fun maxHeight(): Int = 800
}