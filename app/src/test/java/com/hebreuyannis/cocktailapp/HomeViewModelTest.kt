package com.hebreuyannis.cocktailapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hebreuyannis.cocktailapp.viewmodel.DetailViewModel
import com.hebreuyannis.cocktailapp.viewmodel.MainViewModel
import com.hebreuyannis.data_remote.exception.LoadingException
import com.hebreuyannis.data_remote.exception.NetworkException
import com.hebreuyannis.domain.usecases.GetRandomDrinksUseCase
import com.hebreuyannis.domain.usecases.SearchDrinksUseCase
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.hebreuyannis.domain.models.Result
import com.hebreuyannis.domain.repository.IDrinkRepository
import com.nhaarman.mockitokotlin2.lastValue
import junit.framework.Assert.assertEquals
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
import org.mockito.Mockito.*
import java.lang.Exception

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    @Suppress("unused")
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var mockCommandObserver: Observer<MainViewModel.Command>

    @Captor
    private lateinit var commandCaptor: ArgumentCaptor<MainViewModel.Command>

    @Mock
    private lateinit var repository: IDrinkRepository

    private lateinit var getRandomDrinksUseCase: GetRandomDrinksUseCase

    private lateinit var searchDrinksUseCase: SearchDrinksUseCase

    private lateinit var viewModelTest: MainViewModel

    @Before
    fun before(){
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)
        searchDrinksUseCase = SearchDrinksUseCase(DefaultDispatcherProvider().io(),repository)
        getRandomDrinksUseCase = GetRandomDrinksUseCase(DefaultDispatcherProvider().io(),repository)
        viewModelTest = MainViewModel(getRandomDrinksUseCase,searchDrinksUseCase)
        viewModelTest.command.observeForever(mockCommandObserver)
    }

    @After
    fun after() {
        viewModelTest.command.removeObserver(mockCommandObserver)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}