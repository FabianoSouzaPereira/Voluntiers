package com.fabianodev.voluntiers.presentation.widgets.shimmer

import android.content.Context
import android.view.ViewGroup
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout

object ShimmerUtil {
    fun createShimmerLayout(context: Context): ShimmerFrameLayout {
        val shimmer = Shimmer.ColorHighlightBuilder().setBaseAlpha(0.9f)
                .setHighlightAlpha(0.8f)
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()

        return ShimmerFrameLayout(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setShimmer(shimmer)
        }
    }
}
