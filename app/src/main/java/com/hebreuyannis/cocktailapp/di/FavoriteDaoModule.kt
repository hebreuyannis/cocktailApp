package com.hebreuyannis.cocktailapp.di

import android.content.Context
import androidx.room.Room
import com.hebreuyannis.data_local.DrinkDatabase
import com.hebreuyannis.data_local.dao.FavoriteDao
import com.hebreuyannis.data_local.repository.FavoriteRepository
import com.hebreuyannis.domain.repository.IFavoriteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FavoriteDaoModule {


    @Provides
    @Singleton
    fun providesDrinkDataBase(context: Context): DrinkDatabase {
        return Room
            .databaseBuilder(context, DrinkDatabase::class.java, "drink_db")
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