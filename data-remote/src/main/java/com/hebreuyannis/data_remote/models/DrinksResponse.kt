package com.hebreuyannis.data_remote.models

import com.squareup.moshi.Json

data class DrinksResponse(@field:Json(name = "drinks") val drinks: List<DrinkResponse>)
data class DrinkResponse(
    @field:Json(name = "idDrink") val id: String,
    @field:Json(name = "strDrink") val name: String,
    @field:Json(name = "strTags") val tags: String?,
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
    @field:Json(name = "strIngredient7") val ingredient7: String?,
    @field:Json(name = "strIngredient8") val ingredient8: String?,
    @field:Json(name = "strIngredient9") val ingredient9: String?,
    @field:Json(name = "strIngredient10") val ingredient10: String?,
    @field:Json(name = "strIngredient11") val ingredient11: String?,
    @field:Json(name = "strIngredient12") val ingredient12: String?,
    @field:Json(name = "strIngredient13") val ingredient13: String?,
    @field:Json(name = "strIngredient14") val ingredient14: String?,
    @field:Json(name = "strIngredient15") val ingredient15: String?,
)