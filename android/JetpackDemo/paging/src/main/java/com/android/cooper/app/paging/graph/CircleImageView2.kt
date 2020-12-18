package com.android.cooper.app.paging.graph

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.android.cooper.app.paging.R
import kotlin.math.min

/**
 * Created by cooper
 * 20-12-17.
 * Email: 1239604859@qq.com
 */
class CircleImageView2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Graph(context, attrs, defStyleAttr) {
    private var size = 0F
    private val bitmap: Bitmap
    private val paint = Paint()

    // SRC_ATOP:在源图像和目标图像相交的地方绘制【源图像】，在不相交的地方绘制【目标图像】
    // 相交处的效果受到源图像和目标图像alpha的影响
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)

    override fun maxWidth(): Int = 2000

    override fun maxHeight(): Int = 700

    init {
        val head = BitmapFactory.decodeResource(context.resources, R.drawable.ic_head)
        bitmap = big(head)
        paint.isAntiAlias = true
        paint.isDither = true
        paint.isFilterBitmap = true

        val w = bitmap.width
        val h = bitmap.height
        size = min(w, h).toFloat()
    }

    private fun makeCircle(): Bitmap {
        val circle = Bitmap.createBitmap(size.toInt(), size.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(circle)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL
        val r = size / 3
        canvas.drawCircle(r, r, r, paint)
        return circle
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawCircleImagePro(canvas)
    }

    /**
     * clipPath 实现的圆形图像是有锯齿的，我们可以用 PorterDuffXfermode 来实现无锯齿的圆形图片
     * @param canvas Canvas?
     */
    private fun drawCircleImagePro(canvas: Canvas?) {
        canvas?.apply {
            val sc = saveLayer(0F, 0F, size, size, paint)
            val dst = makeCircle()
            canvas.drawBitmap(dst, 0F, 0F, paint)

            paint.xfermode = xfermode
            canvas.drawBitmap(bitmap, 0F, 0F, paint)
            paint.xfermode = null
            canvas.restoreToCount(sc)
        }
    }
}