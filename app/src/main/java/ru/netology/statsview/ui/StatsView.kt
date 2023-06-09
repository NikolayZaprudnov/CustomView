package ru.netology.statsview.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import ru.netology.statsview.R
import ru.netology.statsview.utils.AndroidUtils
import kotlin.math.min
import kotlin.random.Random

class StatsView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attributeSet, defStyleAttr, defStyleRes) {

    private var textSize = AndroidUtils.dp(context, 20).toFloat()
    private var lineWidth = AndroidUtils.dp(context, 5)
    private var colors = emptyList<Int>()

    init {
        context.withStyledAttributes(attributeSet, R.styleable.StatsView) {
            textSize = getDimension(R.styleable.StatsView_textSize, textSize)
            lineWidth = getDimension(R.styleable.StatsView_lineWidth, lineWidth.toFloat()).toInt()

            colors = listOf(
                getColor(R.styleable.StatsView_color1, generateRandomColor()),
                getColor(R.styleable.StatsView_color2, generateRandomColor()),
                getColor(R.styleable.StatsView_color3, generateRandomColor()),
                getColor(R.styleable.StatsView_color4, generateRandomColor()))
        }
    }

//    var data: List<Float> = emptyList()
//        set(value) {
//            field = value
//            invalidate()
//
//        }
    var data:Float = 0F
    set(value) {
        field = value
        invalidate()
    }
    private var radius = 0F
    private var center = PointF()
    private var oval = RectF()

    private val paint = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        strokeWidth = lineWidth.toFloat()
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }
    private val greyPaint = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        strokeWidth = lineWidth.toFloat()
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }
    private val paintText = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        textSize = this@StatsView.textSize
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = min(w, h) / 2F - lineWidth / 2
        center = PointF(w / 2F, h / 2F)
        oval = RectF(
            center.x - radius,
            center.y - radius,
            center.x + radius,
            center.y + radius
        )
    }

    override fun onDraw(canvas: Canvas) {
//        if (data.isEmpty()) {
//            return
//        }
//        var all = data.sum()
        var startAngle = -90F
        paint.color =  0xff00ffff.toInt()
        greyPaint.color = 0xff808080.toInt()
        var sweepAngle = data*3.6F
        canvas.drawArc(oval, startAngle, sweepAngle, false, paint)
        canvas.drawArc(oval, sweepAngle-90F, 360F-sweepAngle, false, greyPaint)
//        when(data){
//            in 0F..25F -> {
//                canvas.drawArc(oval, startAngle, 90F, false, paint)
//                canvas.drawArc(oval, 0F, 270F, false, greyPaint)
//            }
//            in 25F..50F ->{
//                canvas.drawArc(oval, startAngle, 180F, false, paint)
//                canvas.drawArc(oval, 90F, 180F, false, greyPaint)
//            }
//            in 50F..75F ->{
//                canvas.drawArc(oval, startAngle, 270F, false, paint)
//                canvas.drawArc(oval, 180F, 90F, false, greyPaint)
//            }
//            in 75F..100F ->canvas.drawArc(oval, startAngle, 360F, false, paint)
//        }
//        data.forEachIndexed {index, datum ->
//            val dsg = datum / all
//            val angle = dsg * 360F
//            paint.color = colors.getOrNull(index) ?: generateRandomColor()
//            canvas.drawArc(oval, startAngle, angle, false, paint)
//            startAngle += angle
//        }
        canvas.drawText(
            "%.2f%%".format(data),
            center.x,
            center.y + paintText.textSize / 4,
            paintText
        )
    }

    private fun generateRandomColor() = Random.nextInt(0xff000000.toInt(), 0xFFFFFFFF.toInt())

}
