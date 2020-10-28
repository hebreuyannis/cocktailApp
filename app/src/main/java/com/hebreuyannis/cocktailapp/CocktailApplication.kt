package com.hebreuyannis.cocktailapp

import android.app.Application
import com.hebreuyannis.cocktailapp.di.AppComponent
import com.hebreuyannis.cocktailapp.di.DaggerAppComponent

open class CocktailApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}