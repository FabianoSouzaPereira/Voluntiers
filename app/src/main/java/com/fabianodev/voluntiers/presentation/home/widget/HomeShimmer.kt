package com.fabianodev.voluntiers.presentation.home.widget

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import com.fabianodev.voluntiers.presentation.widgets.shimmer.ShimmerUtil
import com.facebook.shimmer.ShimmerFrameLayout

/**
 * Created by Fabiano Pereira on 26/01/2024.
 */
class HomeShimmer {
    fun shimmer(context: Context, first: Boolean): ShimmerFrameLayout {
        return ShimmerUtil.createShimmerLayout(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val linearLayoutCompat = LinearLayoutCompat(context).apply {
                id = ViewCompat.generateViewId()
                orientation = LinearLayoutCompat.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ).also { lp ->
                    val marginInPixels = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics
                    ).toInt()
                    lp.setMargins(marginInPixels, marginInPixels, marginInPixels, 1)
                }
                val card1 = CardView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT
                    ).also { lp ->
                        val marginInPixels = TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics
                        ).toInt()
                        lp.setMargins(marginInPixels, marginInPixels, marginInPixels, marginInPixels)
                    }
                    val textView1 = TextView(context).apply {
                        setBackgroundColor(Color.DKGRAY)
                        setTextColor(Color.BLACK)
                        layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        text = "xxxxxxxxxxxxxx"
                    }
                    val textView2 = TextView(context).apply {
                        setBackgroundColor(Color.DKGRAY)
                        setTextColor(Color.BLACK)
                        layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        text = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
                    }
                    if (first) {
                        addView(textView1)
                    } else {
                        addView(textView2)
                    }
                }
                addView(card1)
            }
            addView(linearLayoutCompat)
        }
    }
}