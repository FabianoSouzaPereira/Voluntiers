package com.fabianodev.voluntiers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.fabianodev.voluntiers.databinding.ActivityMainBinding
import com.fabianodev.voluntiers.di.MainComponent

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var mainComponent: MainComponent
    // private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent =
            (applicationContext as AppApplication).appComponent.mainComponent().create()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}