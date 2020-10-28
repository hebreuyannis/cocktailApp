package com.hebreuyannis.cocktailapp.di

import com.hebreuyannis.data_remote.api.CocktailApiService
import com.hebreuyannis.data_remote.repository.DetailRepository
import com.hebreuyannis.data_remote.repository.DrinkRepository
import com.hebreuyannis.domain.repository.IDetailRepository
import com.hebreuyannis.domain.repository.IDrinkRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDetailRepository(apiService: CocktailApiService): IDetailRepository {
        return DetailRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideDrinkRepository(apiService: CocktailApiService): IDrinkRepository {
        return DrinkRepository(apiService)
    }

}