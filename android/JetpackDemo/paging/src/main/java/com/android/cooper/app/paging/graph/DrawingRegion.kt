package com.android.cooper.app.paging.graph

import android.content.Context
import android.graphics.*
import android.util.AttributeSet

/**
 * Created by cooper
 * 20-12-16.
 * Email: 1239604859@qq.com
 */
class DrawingRegion @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Graph(context, attrs, defStyleAttr) {
    private val paint = Paint()
    private val r = Rect()
    private val target = Rect()
    private val rect = Rect()
    private val rect2 = Rect()
    private val region = Region()
    private val region2 = Region()
    private val path = Path()
    private val rectF = RectF()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.parseColor("#0172DB"))
        rect.set(100, 100, 300, 300)
        region.set(rect)
        drawRegion(canvas, region)

        rect.set(600, 200, 700, 300)
        target.set(600, 200, 650, 400)
        region.set(rect)
        region.union(target)
        drawRegion(canvas, region)

        cropRegion(canvas)

        // 补集
        regionOp(Region.Op.DIFFERENCE, canvas, 100, 700)
        // 反转补集
        regionOp(Region.Op.REVERSE_DIFFERENCE, canvas, 300, 700)
        // 交集
        regionOp(Region.Op.INTERSECT, canvas, 500, 700)
        // 并集
        regionOp(Region.Op.UNION, canvas, 100, 1000)
        // 异或集: 并集 - 交集
        regionOp(Region.Op.XOR, canvas, 300, 1000)
        // 替换
        regionOp(Region.Op.REPLACE, canvas, 500, 1000)
    }

    private fun drawRegion(canvas: Canvas?, region: Region) {
        val regionIterator = newRegionIterator(region)
        while (regionIterator.next(r)) {
            canvas?.drawRect(r, paint)
        }
    }

    private fun cropRegion(canvas: Canvas?) {
        rectF.set(100F, 500F, 350F, 950F)
        path.reset()
        path.addOval(rectF, Path.Direction.CCW)

        val clip = region
        val originRegion = region2
        clip.set(100, 500, 350, 650)
        originRegion.setPath(path, clip)
        drawRegion(canvas, originRegion)
    }

    private fun regionOp(op: Region.Op, canvas: Canvas?, left: Int, top: Int) {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2F
        val yellowRect = rect
        yellowRect.set(left, top, left + 50, top + 150)
        paint.color = Color.YELLOW
        canvas?.drawRect(yellowRect, paint)

        val greenRect = rect2
        greenRect.set(left - 50, top + 50, left + 100, top + 100)
        paint.color = Color.GREEN
        canvas?.drawRect(greenRect, paint)

        val yellowRegion = region
        val greenRegion = region2
        yellowRegion.set(yellowRect)
        greenRegion.set(greenRect)
        yellowRegion.op(greenRegion, op)

        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        drawRegion(canvas, yellowRegion)
    }

    private fun newRegionIterator(region: Region) = RegionIterator(region)

    override fun maxWidth(): Int = 1500

    override fun maxHeight(): Int = 1500

    init {
        paint.color = Color.RED
    }
}