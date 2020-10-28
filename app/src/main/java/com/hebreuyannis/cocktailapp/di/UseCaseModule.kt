package com.hebreuyannis.cocktailapp.di

import com.hebreuyannis.cocktailapp.DispatcherProvider
import com.hebreuyannis.domain.repository.IDetailRepository
import com.hebreuyannis.domain.repository.IDrinkRepository
import com.hebreuyannis.domain.usecases.GetIngredientsUseCase
import com.hebreuyannis.domain.usecases.GetRandomDrinksUseCase
import com.hebreuyannis.domain.usecases.SearchDrinksUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetIngredientsUseCase(
        dispatcherProvider: DispatcherProvider,
        detailRepository: IDetailRepository
    ): GetIngredientsUseCase {
        return GetIngredientsUseCase(dispatcherProvider.io(), detailRepository)
    }

    @Provides
    fun provideGetRandomDrinksUseCase(
        dispatcherProvider: DispatcherProvider,
        drinkRepository: IDrinkRepository
    ): GetRandomDrinksUseCase {
        return GetRandomDrinksUseCase(dispatcherProvider.io(), drinkRepository)
    }

    @Provides
    fun provideSearchDrinksUseCase(
        dispatcherProvider: DispatcherProvider,
        drinkRepository: IDrinkRepository
    ): SearchDrinksUseCase {
        return SearchDrinksUseCase(dispatcherProvider.io(), drinkRepository)
    }
}