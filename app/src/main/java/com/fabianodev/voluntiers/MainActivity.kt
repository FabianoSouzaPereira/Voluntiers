package com.fabianodev.voluntiers

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.fabianodev.voluntiers.databinding.ActivityMainBinding
import com.fabianodev.voluntiers.di.MainComponent
import com.fabianodev.voluntiers.utils.PreferenceManager

class MainActivity : AppCompatActivity(), MenuProvider {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainComponent: MainComponent
    private var navHostFragment: NavHostFragment? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent =
                (applicationContext as AppApplication).appComponent.mainComponent().create()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.toolbar
        toolbar.setBackgroundColor(Color.BLACK)
        setSupportActionBar(binding.toolbar)
        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.BLACK))
        window.navigationBarColor = Color.BLACK
        window.statusBarColor = Color.BLACK

        addMenuProvider(this)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment?.navController

        navController?.addOnDestinationChangedListener { _, destination, _ ->
            //todo something
        }

        NavigationUI.setupActionBarWithNavController(this, navController!!)
    }


    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_fragment)
        return navController!!.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (navController == null) {
            return false
        }
        return when (menuItem.itemId) {
            R.id.mainFragment -> {
                navController?.navigate(R.id.mainFragment)
                true
            }

            R.id.home -> {
                navController?.navigate(R.id.homeFragment)
                true
            }

            R.id.settings -> {
                navController?.navigate(R.id.settingsFragment)
                true
            }

            R.id.login -> {
                navController?.navigate(R.id.loginFragment)
                true
            }

            R.id.user -> {
                navController?.navigate(R.id.userFragment)
                true
            }

            R.id.logout -> {
                val preferenceManager = PreferenceManager(this)
                preferenceManager.savePreferenceString("token", "")
                navController?.navigate(R.id.loginFragment)
                true
            }

            else -> false
        }
    }
}

