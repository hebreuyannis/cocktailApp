package com.hebreuyannis.cocktailapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hebreuyannis.cocktailapp.SingleLiveEvent
import com.hebreuyannis.cocktailapp.mappers.toPresentation
import com.hebreuyannis.cocktailapp.models.DrinkPresentation
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.usecases.DeleteAllFavoriteUseCase
import com.hebreuyannis.domain.usecases.DeleteFavoriteByNameUseCase
import com.hebreuyannis.domain.usecases.GetAllFavoriteUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val deleteFavoriteByNameUseCase: DeleteFavoriteByNameUseCase,
    private val deleteAllFavoriteUseCase: DeleteAllFavoriteUseCase,
    private val getAllFavoriteUseCase: GetAllFavoriteUseCase,
) : ViewModel() {

    val command = SingleLiveEvent<Command>()

    sealed class Command {
        object Loading :Command()
        data class Data(val drinskPresentation: List<DrinkPresentation>):Command()
    }

    fun loadData(){
        command.value = Command.Loading
        viewModelScope.launch {
            getAllFavoriteUseCase(Unit).collect {
                when(it){
                    is Result.Success -> command.value = Command.Data(it.data.map { favorite -> favorite.toPresentation() })
                    is Result.Error -> Unit
                    Result.Loading -> Unit
                }
            }
        }
    }

    fun deleteItem(drinkPresentation: DrinkPresentation){
        viewModelScope.launch {
            deleteFavoriteByNameUseCase(drinkPresentation.name).collect {result ->
                when(result){
                    is Result.Success -> {
                        loadData()
                    }
                    is Result.Error -> Unit
                    Result.Loading -> Unit
                }
            }
        }
    }

}