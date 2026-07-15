package com.jdca.proyectofinal.UI_Buscadores

import android.content.Context
import android.graphics.Matrix
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.doOnLayout
import kotlin.math.min

class ZoomImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {

    private val matrix = Matrix()
    private val originalMatrix = Matrix()
    private var isZoomed = false
    private var lastTouch = PointF()

    private val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            if (!isZoomed) {
                val scaleFactor = 2f
                matrix.set(originalMatrix)
                matrix.postScale(scaleFactor, scaleFactor, e.x, e.y)
                isZoomed = true
                imageMatrix = matrix
            }
            return true
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            if (isZoomed) {
                matrix.set(originalMatrix)
                isZoomed = false
                imageMatrix = matrix
            }
            return true
        }
    })

    init {
        imageMatrix = matrix
        scaleType = ScaleType.MATRIX

        doOnLayout {
            if (drawable == null) return@doOnLayout
            val viewWidth = width.toFloat()
            val viewHeight = height.toFloat()
            val drawableWidth = drawable.intrinsicWidth.toFloat()
            val drawableHeight = drawable.intrinsicHeight.toFloat()
            val scale = min(viewWidth / drawableWidth, viewHeight / drawableHeight)
            val dx = (viewWidth - drawableWidth * scale) / 2
            val dy = (viewHeight - drawableHeight * scale) / 2
            matrix.setScale(scale, scale)
            matrix.postTranslate(dx, dy)
            originalMatrix.set(matrix)
            imageMatrix = matrix
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        if (isZoomed) {
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    lastTouch.set(event.x, event.y)
                }
                MotionEvent.ACTION_MOVE -> {
                    val dx = event.x - lastTouch.x
                    val dy = event.y - lastTouch.y
                    val bounds = getImageBounds()
                    val viewRect = RectF(0f, 0f, width.toFloat(), height.toFloat())
                    var deltaX = dx
                    var deltaY = dy
                    if (bounds.width() <= viewRect.width()) {
                        deltaX = 0f
                    } else {
                        if (bounds.left + dx > 0) deltaX = -bounds.left
                        if (bounds.right + dx < viewRect.right) deltaX = viewRect.right - bounds.right
                    }
                    if (bounds.height() <= viewRect.height()) {
                        deltaY = 0f
                    } else {
                        if (bounds.top + dy > 0) deltaY = -bounds.top
                        if (bounds.bottom + dy < viewRect.bottom) deltaY = viewRect.bottom - bounds.bottom
                    }
                    matrix.postTranslate(deltaX, deltaY)
                    imageMatrix = matrix
                    lastTouch.set(event.x, event.y)
                }
            }
        }
        return true
    }

    private fun getImageBounds(): RectF {
        val d = drawable ?: return RectF()
        val rect = RectF(0f, 0f, d.intrinsicWidth.toFloat(), d.intrinsicHeight.toFloat())
        matrix.mapRect(rect)
        return rect
    }
}
