package com.hebreuyannis.data_remote.models

import com.squareup.moshi.Json

data class DrinksResponse(
    @field:Json(name = "idDrink") val id: String,
    @field:Json(name = "strDrink") val name: String,
    @field:Json(name = "strTags") val tags: String,
    @field:Json(name = "strCategory") val category: String,
    @field:Json(name = "strIBA") val iba: String,
    @field:Json(name = "strAlcoholic") val alcoholic: String,
    @field:Json(name = "strGlass") val typeGlass: String,
    @field:Json(name = "strInstructions") val instruction: String,
    @field:Json(name = "strDrinkThumb") val drinkThumb: String,
    @field:Json(name = "strIngredient1") val ingredient1: String?,
    @field:Json(name = "strIngredient2") val ingredient2: String?,
    @field:Json(name = "strIngredient3") val ingredient3: String?,
    @field:Json(name = "strIngredient4") val ingredient4: String?,
    @field:Json(name = "strIngredient5") val ingredient5: String?,
    @field:Json(name = "strIngredient6") val ingredient6: String?,
) {
}