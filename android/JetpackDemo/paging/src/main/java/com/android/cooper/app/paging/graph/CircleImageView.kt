package com.android.cooper.app.paging.graph

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.android.cooper.app.paging.R

/**
 * Created by cooper
 * 20-12-17.
 * Email: 1239604859@qq.com
 */
class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Graph(context, attrs, defStyleAttr) {
    private val bitmap: Bitmap
    private val paint = Paint()
    private val path = Path()
    private val rect = Rect()

    override fun maxWidth(): Int = 2000

    override fun maxHeight(): Int = 700

    init {
        val head = BitmapFactory.decodeResource(context.resources, R.drawable.ic_head)
        bitmap = big(head)
        val w = bitmap.width.toFloat()
        val h = bitmap.height.toFloat()

        val x = w / 2
        val y = h / 2
        path.addCircle(x, y, w / 3F, Path.Direction.CCW)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawCircleImage(canvas)
    }

    private fun drawCircleImage(canvas: Canvas?) {
        canvas?.apply {
            save()
            clipPath(path)
            drawBitmap(bitmap, 0F, 0F, paint)

            restore()
            paint.color = Color.RED
            rect.set(900, 100, 1000, 200)
            drawRect(rect, paint)
        }
    }
}