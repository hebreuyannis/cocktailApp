package com.hebreuyannis.cocktailapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DrinkPresentation(
    val name: String,
    val tag: String,
    val category: String,
    val alcoholic: String,
    val typeGlass: String,
    val instruction: String,
    val drinkThumb: String,
) : Parcelable