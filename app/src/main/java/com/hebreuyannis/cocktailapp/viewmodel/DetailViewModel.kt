package com.hebreuyannis.cocktailapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hebreuyannis.cocktailapp.SingleLiveEvent
import com.hebreuyannis.cocktailapp.SnackbarMessage
import com.hebreuyannis.cocktailapp.mappers.toPresentation
import com.hebreuyannis.cocktailapp.models.IngredientPresentation
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.usecases.GetIngredientsUseCase
import com.hebreuyannis.domain.usecases.SearchDrinksUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val searchDrinksUseCase: SearchDrinksUseCase,
    private val getIngredientsUseCase: GetIngredientsUseCase
) : ViewModel() {

    val snackbarMessage = MutableLiveData<SnackbarMessage>()
    val command: SingleLiveEvent<Command> = SingleLiveEvent()

    sealed class Command {
        data class DisplayData(val data: List<IngredientPresentation>) : Command()
        object Loading : Command()
        data class Error(val exception: Exception) : Command()
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
}