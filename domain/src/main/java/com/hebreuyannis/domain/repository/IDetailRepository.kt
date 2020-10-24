package com.hebreuyannis.domain.repository

import com.hebreuyannis.domain.models.Ingredient
import com.hebreuyannis.domain.models.Result
import kotlinx.coroutines.flow.Flow

interface IDetailRepository {

    fun getIngredients(ingredients: List<String>): Flow<Result<List<Ingredient>>>
}