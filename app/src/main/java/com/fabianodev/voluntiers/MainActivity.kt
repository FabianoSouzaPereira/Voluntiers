package com.fabianodev.voluntiers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.fabianodev.voluntiers.databinding.ActivityMainBinding
import com.fabianodev.voluntiers.ui.di.MainComponent

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var mainComponent: MainComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent =
            (applicationContext as AppApplication).appComponent.mainComponent().create()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}