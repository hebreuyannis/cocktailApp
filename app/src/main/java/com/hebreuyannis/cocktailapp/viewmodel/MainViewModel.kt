package com.hebreuyannis.cocktailapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hebreuyannis.cocktailapp.SingleLiveEvent
import com.hebreuyannis.cocktailapp.SnackbarMessage
import com.hebreuyannis.cocktailapp.mappers.toPresentation
import com.hebreuyannis.cocktailapp.models.DrinkPresentation
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.usecases.GetRandomDrinksUseCase
import com.hebreuyannis.domain.usecases.SearchDrinksUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getRandomDrinksUseCase: GetRandomDrinksUseCase,
    private val searchDrinksUseCase: SearchDrinksUseCase
) : ViewModel() {

    val command: SingleLiveEvent<Command> = SingleLiveEvent()
    val currentPresentation = MutableLiveData<List<DrinkPresentation>>()
    val snackbarMessage = MutableLiveData<SnackbarMessage>()

    sealed class Command {
        object Loading : Command()
        data class Error(val exception: Exception) : Command()
    }

    init {
        randomLoad()
    }

    fun randomLoad() {
        viewModelScope.launch {
            getRandomDrinksUseCase.invoke(Unit).collect {
                when (it) {
                    is Result.Success -> currentPresentation.value =
                        it.data.map { drink -> drink.toPresentation() }
                    is Result.Error -> command.value = Command.Error(it.exception)
                    Result.Loading -> command.value = Command.Loading
                }
            }
        }
    }

    fun searchByName(name: String) {
        viewModelScope.launch {
            searchDrinksUseCase.invoke(name).collect {
                when (it) {
                    is Result.Success -> currentPresentation.value =
                        it.data.map { drink -> drink.toPresentation() }
                    is Result.Error -> command.value = Command.Error(it.exception)
                    Result.Loading -> command.value = Command.Loading
                }
            }
        }
    }

}