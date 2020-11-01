package com.hebreuyannis.cocktailapp

import com.hebreuyannis.cocktailapp.di.AppComponent

class TestCocktailApplication: CocktailApplication() {

    override fun initializeComponent(): AppComponent {
        // Creates a new TestAppComponent that injects fakes types
        return DaggerTestAppComponent.create()
    }
}