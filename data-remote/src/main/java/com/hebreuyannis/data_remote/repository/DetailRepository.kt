@file:Suppress("BlockingMethodInNonBlockingContext")

package com.hebreuyannis.data_remote.repository

import com.hebreuyannis.data_remote.api.CocktailApiService
import com.hebreuyannis.data_remote.mappers.toDomain
import com.hebreuyannis.domain.models.Ingredient
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.repository.IDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailRepository(private val apiService: CocktailApiService) : IDetailRepository {

    override fun getIngredients(ingredients: List<String>): Flow<Result<List<Ingredient>>> {
        return flow {
            emit(Result.Loading)
            val listIngredients = ArrayList<Ingredient>()
            for (ingredient in ingredients) {
                val callable = apiService.searchIngredientByName(ingredient)
                val ingredientRresponse = callable.execute()
                listIngredients.add(ingredientRresponse.body()!!.toDomain())
            }
            emit(Result.Success(listIngredients))
        }
    }

}