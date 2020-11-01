package com.hebreuyannis.cocktailapp

import com.hebreuyannis.cocktailapp.di.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[CoroutinesModule::class, NetworkModule::class, RepositoryModule::class, UseCaseModule::class, SnackbarModule::class,FakeFavoriteModule::class])
interface TestAppComponent : AppComponent