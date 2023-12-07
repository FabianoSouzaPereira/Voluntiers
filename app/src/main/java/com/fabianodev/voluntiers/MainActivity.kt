package com.fabianodev.voluntiers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.fabianodev.voluntiers.databinding.ActivityMainBinding
import com.fabianodev.voluntiers.di.MainComponent
import com.fabianodev.voluntiers.presentation.login.LoginFragment

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var mainComponent: MainComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent =
            (applicationContext as AppApplication).appComponent.mainComponent().create()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment())
            .commit()

    }
}