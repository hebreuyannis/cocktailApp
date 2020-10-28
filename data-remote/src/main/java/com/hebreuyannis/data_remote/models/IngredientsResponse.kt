package com.hebreuyannis.data_remote.models

import com.squareup.moshi.Json

data class IngredientsResponse(@field:Json(name = "ingredients") val ingredients: List<IngredientResponse>)

data class IngredientResponse(
    @field:Json(name = "idIngredient") val id: String,
    @field:Json(name = "strIngredient") val name: String,
    @field:Json(name = "strDescription") val description: String?,
    @field:Json(name = "strType") val type: String?,
    @field:Json(name = "strAlcohol") val alcohol: String?,
    @field:Json(name = "strABV") val abv: String?,
)