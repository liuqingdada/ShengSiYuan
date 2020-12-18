package com.android.cooper.app.paging.graph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet

/**
 * Created by cooper
 * 20-12-17.
 * Email: 1239604859@qq.com
 */
class DrawingCanvas2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Graph(context, attrs, defStyleAttr) {
    private val rect = Rect()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.RED)
        canvas?.save()

        rect.set(100, 100, 700, 700)
        canvas?.clipRect(rect)
        canvas?.drawColor(Color.GREEN)
        canvas?.save()

        rect.set(200, 200, 600, 600)
        canvas?.clipRect(rect)
        canvas?.drawColor(Color.BLUE)
        canvas?.save()

        rect.set(300, 300, 500, 500)
        canvas?.clipRect(rect)
        canvas?.drawColor(Color.BLACK)
        canvas?.save()

        rect.set(350, 350, 450, 450)
        canvas?.clipRect(rect)
        canvas?.drawColor(Color.WHITE)
        canvas?.save()

        canvas?.restore()
        canvas?.restore()
        canvas?.drawColor(Color.YELLOW)
    }

    override fun maxWidth(): Int = 800

    override fun maxHeight(): Int = 800
}