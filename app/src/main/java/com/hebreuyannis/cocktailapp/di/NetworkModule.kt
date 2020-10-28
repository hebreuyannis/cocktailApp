package com.hebreuyannis.cocktailapp.di

import com.hebreuyannis.data_remote.api.CocktailApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(apiRequestInterceptor: ApiRequestInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(apiRequestInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun apiRequestInterceptor(): ApiRequestInterceptor {
        return ApiRequestInterceptor()
    }

    @Provides
    fun provideCocktailService(retrofit: Retrofit): CocktailApiService =
        retrofit.create(CocktailApiService::class.java)
}