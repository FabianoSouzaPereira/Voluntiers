package com.fabianodev.voluntiers.presentation.widgets.rvTaskItem

/**
 * Created by Fabiano Pereira on 19/01/2024.
 */
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

class CustomImageViewWithText(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private val imageView: ImageView
    private val textView: TextView

    init {
        /* Setup ImageView */
        imageView = ImageView(context)
        imageView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.alpha = 0.3f

        /* Setup  FrameLayout */
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        /* Setup TextView */
        textView = TextView(context)
        val textParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        textParams.gravity = Gravity.CENTER
        textView.layoutParams = textParams
        textView.setTextColor(Color.WHITE)
        textView.textSize = 40f

        addView(imageView)
        addView(textView)
    }

    fun setImageResource(resourceId: Int) {
        imageView.setImageResource(resourceId)
    }

    fun setText(text: String) {
        textView.text = text
    }
}
