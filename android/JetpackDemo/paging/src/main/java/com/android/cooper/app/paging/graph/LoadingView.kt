package com.android.cooper.app.paging.graph

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.android.cooper.app.paging.R

/**
 * Created by cooper
 * 20-12-18.
 * Email: 1239604859@qq.com
 */
class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Graph(context, attrs, defStyleAttr) {
    private var totalW = 0F
    private var totalH = 0F
    private val bitmap: Bitmap
    private val paint = Paint()

    // SRC_IN:只在源图像和目标图像相交的地方绘制【源图像】
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    private var currentTop: Float
    private var rectF = RectF()

    override fun maxWidth(): Int = 1000

    override fun maxHeight(): Int = 700

    init {
        val head = BitmapFactory.decodeResource(context.resources, R.drawable.ic_head)
        bitmap = big(head)
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.isDither = true
        paint.isFilterBitmap = true
        currentTop = bitmap.height.toFloat()
        // 矩形的高度为0
        rectF.set(0F, currentTop, bitmap.width.toFloat(), bitmap.height.toFloat())
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        totalW = w.toFloat()
        totalH = h.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        rectF.top = currentTop
        canvas?.apply {
            /**
             * 设置View的离屏缓冲。在绘图的时候新建一个“层”，所有的操作都在该层而不会影响该层以外的图像
             * 必须设置，否则设置的PorterDuffXfermode会无效，具体原因不明
             */
            val sc = saveLayer(0F, 0F, totalW, totalH, paint)

            drawBitmap(bitmap, 0F, 0F, null) // DST
            paint.xfermode = xfermode
            paint.color = Color.RED
            drawRect(rectF, paint)           // SRC
            paint.xfermode = null

            restoreToCount(sc)
            if (currentTop > 0) {
                currentTop--
                postInvalidate()
            }
        }
    }
}