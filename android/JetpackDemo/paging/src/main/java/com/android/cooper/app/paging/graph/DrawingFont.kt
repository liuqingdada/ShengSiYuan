package com.android.cooper.app.paging.graph

import android.content.Context
import android.content.pm.PackageItemInfo
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet

/**
 * Created by cooper
 * 20-12-16.
 * Email: 1239604859@qq.com
 *
 * [Paint.setStrokeWidth]
 * [Paint.setAntiAlias]
 * [Paint.setStyle]
 * [Paint.setTextAlign]      对齐方式
 * [Paint.setTextSize]       文字大小
 * [Paint.setFakeBoldText]   粗体文字
 * [Paint.setUnderlineText]  下划线
 * [Paint.setTextSkewX]      文字水平倾斜度, 普通斜体字设为 0.25
 * [Paint.setStrikeThruText] 删除线
 * [Paint.setDither]         设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
 * [Paint.setFilterBitmap]   加快显示速度，本设置项依赖于dither和xfermode的设置
 */
class DrawingFont @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Graph(context, attrs, defStyleAttr) {
    private val paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawText(canvas, null, 20F)
        drawText(canvas, Paint.Style.STROKE, 40F + dp2px(90F))
        drawText(canvas, Paint.Style.FILL_AND_STROKE, 60 + dp2px(180F))

        drawTextAlign(canvas, null, 320F)
        drawTextAlign(canvas, Paint.Align.CENTER, 420F)
        drawTextAlign(canvas, Paint.Align.RIGHT, 520F)

        drawTextScale(canvas)
    }

    private fun drawText(canvas: Canvas?, style: Paint.Style?, start: Float) {
        style?.let { paint.style = it }
        canvas?.drawText("自定义", start, dp2px(70F), paint)
    }

    private fun drawTextAlign(canvas: Canvas?, align: Paint.Align?, top: Float) {
        paint.style = Paint.Style.FILL
        align?.apply {
            paint.textAlign = align
        }
        canvas?.drawText("Hello, 苏", 400F, top, paint)
    }

    private fun drawTextScale(canvas: Canvas?) {
        paint.style = Paint.Style.FILL
        paint.textAlign = Paint.Align.LEFT

        canvas?.drawText("自定义", 20F, 620F, paint)

        paint.textSkewX = -0.25F
        canvas?.drawText("自定义", 20F, 720F, paint)

        paint.textSkewX = 0F
        paint.textScaleX = 2F
        canvas?.drawText("自定义", 20F, 820F, paint)

        paint.textScaleX = 1F
        paint.isFakeBoldText = true
        canvas?.drawText("自定义", 20F, 920F, paint)

        paint.isFakeBoldText = false
        paint.isUnderlineText = true
        canvas?.drawText("自定义", 20F, 1020F, paint)

        paint.isUnderlineText = false
        paint.isStrikeThruText = true
        canvas?.drawText("自定义", 20F, 1120F, paint)
    }

    override fun maxWidth(): Int = 1000

    override fun maxHeight(): Int = 1300

    init {
        paint.color = Color.DKGRAY
        paint.strokeWidth = 3F
        paint.isAntiAlias = true
        paint.textSize = sp2px(30F)
    }
}