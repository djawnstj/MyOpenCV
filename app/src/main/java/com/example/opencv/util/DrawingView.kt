package com.example.opencv.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.example.opencv.AppData
import com.example.opencv.ImgUtil.Companion.toBitmap
import org.opencv.core.*
import org.opencv.imgproc.Imgproc
import kotlin.math.abs
import kotlin.math.sqrt

class DrawingView(context: Context, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    companion object { private const val TAG = "DrawingView" }

    enum class ShapeType {
        LINE,
        CIRCLE,
        RECTANGLE
    }

    private var startX: Float = 0.0F
    private var startY: Float = 0.0F

    private var canvasWidth: Int = 0
    private var canvasHeight: Int = 0
    private lateinit var mat: Mat

    var shapeType: ShapeType = ShapeType.LINE

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasWidth = w
        canvasHeight = h

        mat = Mat.zeros(canvasHeight, canvasWidth, CvType.CV_8UC3)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        AppData.debug(TAG, "onTouchEvent() called.")

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    drawingShapes(event)
                }
            }

        return true
//        return super.onTouchEvent(event)
    }

    private fun drawingShapes(event: MotionEvent) {
        AppData.debug(TAG, "drawingShapes() called: $shapeType")

        mat = Mat.zeros(canvasHeight, canvasWidth, CvType.CV_8UC3)

        when (shapeType) {
            ShapeType.LINE -> {
                Imgproc.line(
                    mat,
                    Point(startX.toDouble(), startY.toDouble()),
                    Point(event.x.toDouble(), event.y.toDouble()),
                    Scalar(255.0, 0.0, 0.0)
                )
            }
            ShapeType.RECTANGLE -> {
                Imgproc.rectangle(
                    mat,
                    Point(startX.toDouble(), startY.toDouble()),
                    Point(event.x.toDouble(), event.y.toDouble()),
                    Scalar(255.0, 0.0, 0.0)
                )
            }
            ShapeType.CIRCLE -> {
                val absX = abs(startX - event.x)
                val absY = abs(startY - event.y)
                Imgproc.circle(
                    mat,
                    Point(startX.toDouble(), startY.toDouble()),
                    sqrt(absX * absX + absY * absY).toInt(),
                    Scalar(255.0, 0.0, 0.0)
                )
            }
        }

        val bitmap = mat.toBitmap()

        setImageBitmap(bitmap)
    }

}