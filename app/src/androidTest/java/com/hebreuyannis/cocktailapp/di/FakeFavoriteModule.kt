package com.hebreuyannis.cocktailapp.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.hebreuyannis.data_local.DrinkDatabase
import com.hebreuyannis.data_local.dao.FavoriteDao
import com.hebreuyannis.data_local.repository.FavoriteRepository
import com.hebreuyannis.domain.repository.IFavoriteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FakeFavoriteModule {

    @Provides
    fun providesContext() : Context {
        return ApplicationProvider.getApplicationContext<Context>()
    }

    @Provides
    @Singleton
    fun providesDrinkDataBase(context: Context): DrinkDatabase {
        return Room
            .inMemoryDatabaseBuilder(context, DrinkDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providesFavoriteRepository(favoriteDao: FavoriteDao): IFavoriteRepository {
        return FavoriteRepository(favoriteDao)
    }

    @Provides
    @Singleton
    fun providesFavoriteDao(dataBase: DrinkDatabase): FavoriteDao {
        return dataBase.favoritesDao()
    }
}