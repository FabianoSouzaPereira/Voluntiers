package com.fabianodev.voluntiers.presentation.widgets

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.facebook.shimmer.ShimmerFrameLayout

/**
 * Created by Fabiano Pereira on 26/01/2024.
 */
class DrawableDivider {
    fun createDivider(context: Context): ShimmerFrameLayout {
        val drawableDivider = ContextCompat.getDrawable(context, androidx.appcompat.R.drawable.abc_action_bar_item_background_material)
        return ShimmerFrameLayout(context).apply {
            setBackgroundColor(Color.BLACK)
            layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    16
                    // drawableDivider?.intrinsicHeight ?: 1
            )
            background = drawableDivider
        }
    }
}