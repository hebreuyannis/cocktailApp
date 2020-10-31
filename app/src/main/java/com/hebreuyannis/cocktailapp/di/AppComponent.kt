package com.hebreuyannis.cocktailapp.di

import android.content.Context
import com.hebreuyannis.cocktailapp.CoroutinesModule
import com.hebreuyannis.cocktailapp.ui.DrinkDetailFragment
import com.hebreuyannis.cocktailapp.ui.FavoriteFragment
import com.hebreuyannis.cocktailapp.ui.HomeFragment
import com.hebreuyannis.cocktailapp.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoroutinesModule::class, NetworkModule::class, RepositoryModule::class, UseCaseModule::class, SnackbarModule::class, FavoriteDaoModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: DrinkDetailFragment)
    fun inject(fragment: FavoriteFragment)

}