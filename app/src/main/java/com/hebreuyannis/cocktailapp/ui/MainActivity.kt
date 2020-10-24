package com.hebreuyannis.cocktailapp.ui

import android.app.SearchManager
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.hebreuyannis.cocktailapp.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationHost,BottomBarBehavior {

    private lateinit var navController: NavController
    private var navHostFragment: NavHostFragment? = null

    companion object {
        private val TOP_LEVEL_DESTINATIONS = setOf(
            R.id.navigation_home,
            R.id.navigation_favorite
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
        val appBarConfiguration = AppBarConfiguration(TOP_LEVEL_DESTINATIONS)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun setupNavigation() {
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        navController = navHostFragment!!.navController
        NavigationUI.setupWithNavController(
            bottom_bar,
            navController
        )
    }

    override fun revealBottomBar() {
        val slideUp: Animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.slide_up
        )
        slideUp.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                bottom_bar.visibility = View.GONE
            }
            override fun onAnimationEnd(animation: Animation?) {
                bottom_bar.clearAnimation()
                bottom_bar.visibility = View.VISIBLE
            }
            override fun onAnimationRepeat(animation: Animation?) = Unit
        })

        bottom_bar.startAnimation(slideUp)
    }

    override fun hideBottomBar() {
        val slideDown: Animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.slide_down
        )
        slideDown.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                bottom_bar.visibility = View.VISIBLE
            }
            override fun onAnimationEnd(animation: Animation?) {
                bottom_bar.clearAnimation()
                bottom_bar.visibility = View.GONE
            }
            override fun onAnimationRepeat(animation: Animation?) = Unit
        })
        bottom_bar.startAnimation(slideDown)
    }
}