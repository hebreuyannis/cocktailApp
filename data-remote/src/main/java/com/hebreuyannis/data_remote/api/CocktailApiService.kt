package com.hebreuyannis.data_remote.api

import com.hebreuyannis.data_remote.models.DrinksResponse
import com.hebreuyannis.data_remote.models.IngredientsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApiService {

    @GET("search.php?")
    fun searchDrinkByName(@Query("s") name: String): Call<DrinksResponse>

    @GET("search.php?")
    fun searchDrinkByLetter(@Query("f") letter: String): Call<DrinksResponse>

    @GET("search.php?")
    fun searchIngredientByName(@Query("i") name: String): Call<IngredientsResponse>

}