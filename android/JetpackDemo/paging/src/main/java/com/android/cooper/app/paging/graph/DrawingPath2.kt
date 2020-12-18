package com.android.cooper.app.paging.graph

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.android.cooper.app.paging.R

/**
 * Created by cooper
 * 20-12-14.
 * Email: 1239604859@qq.com
 */
class DrawingPath2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Graph(context, attrs, defStyleAttr) {
    private var fillType = Path.FillType.WINDING.ordinal

    private val paint = Paint()
    private val path = Path()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.parseColor("#0172DB"))
        paint.isAntiAlias = true
        paint.color = Color.RED
        when (fillType) {
            Path.FillType.WINDING.ordinal -> {
                fillTypeTest(canvas, Path.FillType.WINDING, 100F, 100F)
            }
            Path.FillType.INVERSE_WINDING.ordinal -> {
                fillTypeTest(canvas, Path.FillType.INVERSE_WINDING, 100F, 100F)
            }
            Path.FillType.EVEN_ODD.ordinal -> {
                fillTypeTest(canvas, Path.FillType.EVEN_ODD, 100F, 100F)
            }
            Path.FillType.INVERSE_EVEN_ODD.ordinal -> {
                fillTypeTest(canvas, Path.FillType.INVERSE_EVEN_ODD, 100F, 100F)
            }
        }
    }

    @Suppress("SameParameterValue")
    private fun fillTypeTest(canvas: Canvas?, fillType: Path.FillType, left: Float, top: Float) {
        path.addRect(left, top, left + 150F, top + 150F, Path.Direction.CW)
        path.addCircle(left + 150F, top + 150F, 100F, Path.Direction.CW)
        path.fillType = fillType
        canvas?.drawPath(path, paint)
    }

    override fun maxWidth(): Int = 500

    override fun maxHeight(): Int = 500

    init {
        attrs?.apply {
            val ta = context.obtainStyledAttributes(this, R.styleable.DrawingPath2)
            fillType = ta.getInt(R.styleable.DrawingPath2_fillType, Path.FillType.WINDING.ordinal)
            ta.recycle()
        }
    }
}