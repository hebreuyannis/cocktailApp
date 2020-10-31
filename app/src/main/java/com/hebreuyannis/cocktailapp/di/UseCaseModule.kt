package com.hebreuyannis.cocktailapp.di

import com.hebreuyannis.cocktailapp.DispatcherProvider
import com.hebreuyannis.domain.repository.IDetailRepository
import com.hebreuyannis.domain.repository.IDrinkRepository
import com.hebreuyannis.domain.repository.IFavoriteRepository
import com.hebreuyannis.domain.usecases.*
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

    @Provides
    fun provideInsertFavoriteUseCase(
        dispatcherProvider: DispatcherProvider,
        favoriteRepository: IFavoriteRepository
    ): InsertFavoriteUseCase {
        return InsertFavoriteUseCase(dispatcherProvider.io(), favoriteRepository)
    }

    @Provides
    fun provideGetFavoriteByName(
        dispatcherProvider: DispatcherProvider,
        favoriteRepository: IFavoriteRepository
    ): GetFavoriteByName {
        return GetFavoriteByName(dispatcherProvider.io(), favoriteRepository)
    }

    @Provides
    fun providesDeleteFavoriteByNameUseCase(
        dispatcherProvider: DispatcherProvider,
        favoriteRepository: IFavoriteRepository
    ): DeleteFavoriteByNameUseCase {
        return DeleteFavoriteByNameUseCase(dispatcherProvider.io(), favoriteRepository)
    }

    @Provides
    fun providesDeleteAllFavoriteUseCase(
        dispatcherProvider: DispatcherProvider,
        favoriteRepository: IFavoriteRepository
    ): DeleteAllFavoriteUseCase {
        return DeleteAllFavoriteUseCase(dispatcherProvider.io(), favoriteRepository)
    }

    @Provides
    fun providesGetAllFavoriteUseCase(
        dispatcherProvider: DispatcherProvider,
        favoriteRepository: IFavoriteRepository
    ): GetAllFavoriteUseCase {
        return GetAllFavoriteUseCase(dispatcherProvider.io(), favoriteRepository)
    }
}