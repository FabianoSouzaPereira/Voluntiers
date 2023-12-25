package com.fabianodev.voluntiers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fabianodev.voluntiers.databinding.ActivityMainBinding
import com.fabianodev.voluntiers.di.MainComponent
import com.fabianodev.voluntiers.presentation.main.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent =
            (applicationContext as AppApplication).appComponent.mainComponent().create()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.nav_host_fragment, MainFragment())
            .commit()
    }
}