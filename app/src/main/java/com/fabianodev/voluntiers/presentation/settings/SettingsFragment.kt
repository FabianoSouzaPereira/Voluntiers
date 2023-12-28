package com.fabianodev.voluntiers.presentation.settings

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.preference.PreferenceFragmentCompat
import com.fabianodev.voluntiers.MainActivity
import com.fabianodev.voluntiers.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainComponent.inject(this)
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val frameLayout = FrameLayout(requireContext())

        /** Getting toolbar height dynamically */
        val typedValue = TypedValue()
        if (requireActivity().theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
            val actionBarSize = TypedValue.complexToDimensionPixelSize(
                    typedValue.data,
                    resources.displayMetrics
            )
            val layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            )
            layoutParams.topMargin = actionBarSize
            frameLayout.layoutParams = layoutParams
        }

        val view = super.onCreateView(inflater, frameLayout, savedInstanceState)
        view.let { frameLayout.addView(it) }
        return frameLayout
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}