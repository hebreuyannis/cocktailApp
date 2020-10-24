package com.hebreuyannis.data_remote.api

import com.hebreuyannis.data_remote.models.DrinksResponse
import com.hebreuyannis.data_remote.models.IngredientsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CocktailApiService {

    @GET("search.php?s={name}")
    fun searchDrinkByName(@Path("name") name: String):List<DrinksResponse>

    @GET("search.php?f={letter}")
    fun searchDrinkByLetter(@Path("letter") name: String):List<DrinksResponse>

    @GET("search.php?i=vodka")
    fun searchIngredientByName(@Path("name") name: String):IngredientsResponse

}