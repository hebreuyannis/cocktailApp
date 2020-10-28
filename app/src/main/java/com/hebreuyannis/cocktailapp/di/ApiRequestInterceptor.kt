package com.hebreuyannis.cocktailapp.di

import okhttp3.Interceptor
import okhttp3.Response

class ApiRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .build()

        return chain.proceed(request)
    }
}