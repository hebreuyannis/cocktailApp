package com.hebreuyannis.cocktailapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hebreuyannis.cocktailapp.viewmodel.DetailViewModel
import com.hebreuyannis.cocktailapp.viewmodel.MainViewModel
import com.hebreuyannis.data_remote.exception.NetworkException
import com.hebreuyannis.domain.models.Drink
import com.hebreuyannis.domain.models.Ingredient
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.repository.IDetailRepository
import com.hebreuyannis.domain.repository.IDrinkRepository
import com.hebreuyannis.domain.repository.IFavoriteRepository
import com.hebreuyannis.domain.usecases.*
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    @Suppress("unused")
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var mockCommandObserver: Observer<DetailViewModel.Command>

    @Captor
    private lateinit var commandCaptor: ArgumentCaptor<DetailViewModel.Command>

    @Mock
    private lateinit var repository: IDrinkRepository

    @Mock
    private lateinit var repositoryDetail: IDetailRepository

    @Mock
    private lateinit var repositoryFavorite: IFavoriteRepository

    private lateinit var searchDrinksUseCase: SearchDrinksUseCase
    private lateinit var getIngredientsUseCase: GetIngredientsUseCase
    private lateinit var insertFavoriteUseCase: InsertFavoriteUseCase
    private lateinit var deleteFavoriteByNameUseCase: DeleteFavoriteByNameUseCase
    private lateinit var getFavoriteByName: GetFavoriteByName

    private lateinit var viewModelTest: DetailViewModel

    @Before
    fun before(){
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)
        whenever(repository.getRandomDrinks()).doReturn(flow { emit(Result.Loading) })
        searchDrinksUseCase = SearchDrinksUseCase(DefaultDispatcherProvider().io(),repository)
        getIngredientsUseCase = GetIngredientsUseCase(DefaultDispatcherProvider().io(),repositoryDetail)
        insertFavoriteUseCase = InsertFavoriteUseCase(DefaultDispatcherProvider().io(),repositoryFavorite)
        deleteFavoriteByNameUseCase = DeleteFavoriteByNameUseCase(DefaultDispatcherProvider().io(),repositoryFavorite)
        getFavoriteByName = GetFavoriteByName(DefaultDispatcherProvider().io(),repositoryFavorite)
        viewModelTest = DetailViewModel(searchDrinksUseCase,getIngredientsUseCase,insertFavoriteUseCase,deleteFavoriteByNameUseCase,getFavoriteByName)
    }

    @After
    fun after() {
        viewModelTest.command.removeObserver(mockCommandObserver)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }



}