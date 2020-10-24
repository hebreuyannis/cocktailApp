package com.hebreuyannis.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import com.hebreuyannis.domain.models.Result


abstract class FlowUseCase<in Parameter, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameters: Parameter): Flow<Result<R>> = execute(parameters)
        .catch { e -> emit(Result.Error(Exception(e))) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: Parameter): Flow<Result<R>>
}