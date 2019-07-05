package ovlesser.mmchallenge.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.SeekBar

//SeekBar with progress indicator
//TODO: need to customize the indicator
class MySeekBar : SeekBar {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val thumbX = (this.progress.toDouble() / this.max * this.width.toDouble()).toInt()
        val thumbY = this.height.toFloat() - 100

        val paint = Paint()
        paint.color = Color.BLACK
        paint.textSize = 40F
        c.drawText(this.progress.toString(), thumbX.toFloat(), thumbY, paint)
    }
}