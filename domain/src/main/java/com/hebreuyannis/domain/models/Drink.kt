package com.hebreuyannis.domain.models

data class Drink(
    val name:String,
    val tag:String,
    val category:String,
    val alcoholic:String,
    val typeGlass:String,
    val instruction:String,
    val drinkThumb:String,
    val ingredients:List<String>
    )