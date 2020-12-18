package com.android.cooper.app.paging.graph

import android.content.Context
import android.graphics.*
import android.util.AttributeSet

/**
 * Created by cooper
 * 20-12-14.
 * Email: 1239604859@qq.com
 */
class DrawingPath @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Graph(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private val bgPaint = Paint()
    private val linePath = Path()
    private val arcPath = Path()
    private val arcRectF = RectF()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.isAntiAlias = true
        bgPaint.isAntiAlias = true
        bgPaint.color = Color.DKGRAY

        paint.color = Color.RED
        paint.strokeWidth = 20F
        paint.style = Paint.Style.STROKE

        linePath.moveTo(100F, 100F)
        linePath.lineTo(400F, 300F)
        linePath.lineTo(100F, 300F)
        /**
         * 如果连续画了几条直线，但没有形成闭环，调用 close() 函数会把路径首尾连接起来形成闭环，
         * 相当于是帮我们画多一条直线
         *
         * 如果只画了一条直线，那 close() 方法是不会起作用的
         */
        linePath.close()
        canvas?.drawPath(linePath, paint)


        paint.style = Paint.Style.FILL
        arcRectF.set(100F, 400F, 300F, 600F)
        arcPath.reset()
        arcPath.arcTo(arcRectF, 0F, 90F)
        canvas?.drawOval(arcRectF, bgPaint)
        canvas?.drawPath(arcPath, paint)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10F
        arcRectF.set(400F, 400F, 600F, 600F)
        arcPath.reset()
        arcPath.arcTo(arcRectF, 0F, 90F)
        canvas?.drawOval(arcRectF, bgPaint)
        canvas?.drawPath(arcPath, paint)

        /**
         * 添加路径
         * Path 提供了 addXXX 函数用于添加路径，添加的路径可以是不连续的，还可以是曲线
         */
        linePath.reset()
        linePath.moveTo(100F, 700F)
        linePath.lineTo(180F, 760F)
        arcRectF.set(180F, 760F, 250F, 830F)
        linePath.addArc(arcRectF, 0F, 90F)
        canvas?.drawPath(linePath, paint)

        directionTest(canvas, Path.Direction.CW, 200F)
        directionTest(canvas, Path.Direction.CCW, 400F)
    }

    private fun directionTest(canvas: Canvas?, direction: Path.Direction, start: Float) {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1F
        paint.color = Color.BLUE
        linePath.reset()
        linePath.moveTo(start, 900F)
        arcRectF.set(start, 900F, start + 100F, 1100F)
        linePath.addOval(arcRectF, direction)
        paint.textSize = 20F
        canvas?.drawTextOnPath("一二三", linePath, 0F, 0F, paint)
        canvas?.drawPath(linePath, paint)
    }

    override fun maxWidth(): Int = 1300

    override fun maxHeight(): Int = 1300
}