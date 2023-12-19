package com.fabianodev.voluntiers.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.Button

class ButtonUtil {
    companion object {
        enum class ButtonState(val color: String) {
            PRESSED("#FF4081"),
            ACTIVATED("#00BCD4"),
            DEFAULT("#2196F3")
        }

        fun setGradient(button: Button, textColor: Int, buttonState: ButtonState = ButtonState.DEFAULT, radius: Float = 20f) {
            val shapeDrawable = GradientDrawable()
            shapeDrawable.shape = GradientDrawable.RECTANGLE
            shapeDrawable.cornerRadius = radius

            shapeDrawable.setColor(Color.parseColor(buttonState.color))
            shapeDrawable.setStroke(2, Color.BLACK)

            button.background = shapeDrawable
            button.setTextColor(textColor)
        }
    }
}
