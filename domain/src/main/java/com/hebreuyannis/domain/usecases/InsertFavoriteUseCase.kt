package com.hebreuyannis.domain.usecases

import com.hebreuyannis.domain.models.Favorite
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.repository.IFavoriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class InsertFavoriteUseCase(
    ioDispatcher: CoroutineDispatcher,
    private val favoritesRepository: IFavoriteRepository
) : FlowUseCase<Favorite, Int>(ioDispatcher) {

    override fun execute(parameters: Favorite): Flow<Result<Int>> {
        return favoritesRepository.insertFavorite(parameters)
            .map { observableResult: Result<Int> ->
                when (observableResult) {
                    is Result.Success -> Result.Success(observableResult.data)
                    is Result.Error -> Result.Error(observableResult.exception)
                    Result.Loading -> Result.Loading
                }
            }
    }
}
