package com.hebreuyannis.cocktailapp

data class SnackbarMessage(
    val messageId: Int,
    var actionId: Int? = null,
    val longDuration: Boolean,
    var requestId: String? = null
)