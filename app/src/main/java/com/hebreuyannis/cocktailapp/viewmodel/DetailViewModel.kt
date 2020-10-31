package com.hebreuyannis.cocktailapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hebreuyannis.cocktailapp.R
import com.hebreuyannis.cocktailapp.SingleLiveEvent
import com.hebreuyannis.cocktailapp.SnackbarMessage
import com.hebreuyannis.cocktailapp.mappers.toDomain
import com.hebreuyannis.cocktailapp.mappers.toPresentation
import com.hebreuyannis.cocktailapp.models.DrinkPresentation
import com.hebreuyannis.cocktailapp.models.IngredientPresentation
import com.hebreuyannis.domain.models.Drink
import com.hebreuyannis.domain.models.Favorite
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.usecases.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val searchDrinksUseCase: SearchDrinksUseCase,
    private val getIngredientsUseCase: GetIngredientsUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavoriteByNameUseCase: DeleteFavoriteByNameUseCase,
    private val getFavoriteByName: GetFavoriteByName
) : ViewModel() {

    val snackbarMessage = MutableLiveData<SnackbarMessage>()
    val command: SingleLiveEvent<Command> = SingleLiveEvent()
    val favoriteCommand: SingleLiveEvent<FavoriteCommand> = SingleLiveEvent()
    var isFavorite = false

    sealed class Command {
        data class DisplayData(val data: List<IngredientPresentation>) : Command()
        object Loading : Command()
        data class Error(val exception: Exception) : Command()
    }

    sealed class FavoriteCommand {
        object IsFavorite : FavoriteCommand()
        object IsNotFavorite : FavoriteCommand()
    }

    fun searchInfo(name: String) {
        command.value = Command.Loading
        viewModelScope.launch {
            searchDrinksUseCase.invoke(name).collect {
                when (it) {
                    is Result.Success -> {
                        getIngredientsUseCase.invoke(it.data.first { drink -> drink.name == name }.ingredients)
                            .collect { ingredientResult ->
                                when (ingredientResult) {
                                    is Result.Success -> command.value =
                                        Command.DisplayData(ingredientResult.data.map { ingredient -> ingredient.toPresentation() })
                                    is Result.Error -> command.value =
                                        Command.Error(ingredientResult.exception)
                                    Result.Loading -> command.value = Command.Loading
                                }
                            }
                    }
                    is Result.Error -> Command.Error(it.exception)
                    Result.Loading -> Command.Loading
                }
            }
        }
    }

    fun removeFavorite(drinkPresentation: DrinkPresentation) {
        viewModelScope.launch {
            deleteFavoriteByNameUseCase(drinkPresentation.name).collect {
                when (it) {
                    is Result.Success -> {
                        isFavorite = false
                        favoriteCommand.value = FavoriteCommand.IsNotFavorite
                        snackbarMessage.value =
                            SnackbarMessage(R.string.rm_favorite, null, false, null)

                    }
                    is Result.Error -> Unit
                    Result.Loading -> Unit
                }
            }
        }
    }

    fun isFavorite(drinkPresentation: DrinkPresentation) {
        viewModelScope.launch {
            getFavoriteByName(drinkPresentation.name).collect {
                when (it) {
                    is Result.Success -> {
                        isFavorite = true
                        favoriteCommand.value = FavoriteCommand.IsFavorite
                    }
                    is Result.Error -> {
                        isFavorite = false
                        favoriteCommand.value = FavoriteCommand.IsNotFavorite
                    }
                    Result.Loading -> Unit
                }
            }
        }
    }

    fun addFavorites(drink: Drink) {
        viewModelScope.launch {
            if (command.value is Command.DisplayData) {
                val ingredients = (command.value as Command.DisplayData).data.map { it.toDomain() }
                val favorite = Favorite(
                    drink.name,
                    drink.tag,
                    drink.category,
                    drink.alcoholic,
                    drink.typeGlass,
                    drink.instruction,
                    drink.drinkThumb,
                    ingredients
                )

                insertFavoriteUseCase(favorite).collect {
                    when (it) {
                        is Result.Success -> {
                            isFavorite = true
                            favoriteCommand.value = FavoriteCommand.IsFavorite
                            snackbarMessage.value =
                                SnackbarMessage(R.string.add_favorite, null, false, null)
                        }
                        is Result.Error -> snackbarMessage.value = SnackbarMessage(
                            R.string.wait_until_the_end,
                            R.string.got_it,
                            true,
                            null
                        )
                        else -> Unit
                    }
                }

            } else {
                snackbarMessage.value =
                    SnackbarMessage(R.string.wait_until_the_end, R.string.got_it, true, null)
            }
        }
    }
}