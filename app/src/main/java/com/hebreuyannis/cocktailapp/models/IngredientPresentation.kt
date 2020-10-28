package com.hebreuyannis.cocktailapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IngredientPresentation(
    val name: String,
    val description: String,
    val type: String,
    val ingredientThumb: String,
    val isAlcohol: Boolean
) : Parcelable