package com.gfdellatin.curso_android_marvel_app.presentation.characters

import androidx.paging.PagingData
import androidx.paging.map
import com.gfdellatin.core.domain.model.Character
import com.gfdellatin.core.usecase.GetCharactersUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {

    @ExperimentalCoroutinesApi
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var getCharactersUseCase: GetCharactersUseCase

    private lateinit var charactersViewModel: CharactersViewModel

    private val pagingDataCharacters = PagingData.from(
        listOf(
            Character(
                "3-D Man",
                "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
            ),
            Character(
                "A-Bomb (HAS)",
                "https://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"
            )
        )
    )

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        charactersViewModel = CharactersViewModel(getCharactersUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should validate the paging data object values when calling charactersPagingData`() = runBlockingTest {

        whenever(
            getCharactersUseCase.invoke(any())
        ).thenReturn(
            flowOf(
                pagingDataCharacters
            )
        )

        val result = charactersViewModel.charactersPagingData("")

        assertEquals(1, result.count())
    }

    @ExperimentalCoroutinesApi
    @Test(expected = RuntimeException::class)
    fun `should throw an exception when the calling to the use case returns an exception`() = runBlockingTest {
        whenever(
            getCharactersUseCase.invoke(any())
        ).thenThrow(
            RuntimeException()
        )

        charactersViewModel.charactersPagingData("")
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

}