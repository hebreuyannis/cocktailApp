package com.hebreuyannis.cocktailapp.di

import com.hebreuyannis.cocktailapp.SnackbarMessageManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SnackbarModule {

    @Provides
    @Singleton
    fun providesSnancbarManager(): SnackbarMessageManager {
        return SnackbarMessageManager()
    }
}