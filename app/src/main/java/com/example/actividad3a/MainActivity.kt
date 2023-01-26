package com.example.actividad3a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavigationController(R.id.nav_host_fragment))
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        setupWithNavController(bottomNav, navController)

        bottomNav.isVisible = false

    }

    override fun onSupportNavigateUp (): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController .navigateUp() || super.onSupportNavigateUp()
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment,fragment)
        transaction.commit()
    }
}


fun FragmentActivity.findNavigationController (@IdRes host: Int): NavController {
    try {
        val navHostFragment = supportFragmentManager.findFragmentById(host) as NavHostFragment
        return navHostFragment .findNavController()
    } catch (e: Exception) {
        throw IllegalStateException( "Activity $this does not have a NavController set on $host")
    }
}