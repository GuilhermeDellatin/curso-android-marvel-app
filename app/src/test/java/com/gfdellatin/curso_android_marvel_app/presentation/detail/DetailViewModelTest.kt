package com.gfdellatin.curso_android_marvel_app.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gfdellatin.core.domain.model.Comic
import com.gfdellatin.core.usecase.AddFavoriteUseCase
import com.gfdellatin.core.usecase.CheckFavoriteUseCase
import com.gfdellatin.core.usecase.GetCharacterCategoriesUseCase
import com.gfdellatin.core.usecase.RemoveFavoriteUseCase
import com.gfdellatin.core.usecase.base.ResultStatus
import com.gfdellatin.curso_android_marvel_app.R
import com.gfdellatin.testing.MainCoroutineRule
import com.gfdellatin.testing.model.CharacterFactory
import com.gfdellatin.testing.model.ComicFactory
import com.gfdellatin.testing.model.EventFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase

    @Mock
    private lateinit var checkFavoriteUseCase: CheckFavoriteUseCase

    @Mock
    private lateinit var addFavoriteUseCase: AddFavoriteUseCase

    @Mock
    private lateinit var removeFavoriteUseCase: RemoveFavoriteUseCase

    @Mock
    private lateinit var uiStateObserver: Observer<UiActionStateLiveData.UiState>

    @Mock
    private lateinit var favoriteUiStateObserver: Observer<FavoriteUiActionStateLiveData.UiState>

    private val character = CharacterFactory().create(CharacterFactory.Hero.ThreeDMan)
    private val comics = listOf(ComicFactory().create(ComicFactory.FakeComic.FakeComic1))
    private val events = listOf(EventFactory().create(EventFactory.FakeEvent.FakeEvent1))

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(
            getCharacterCategoriesUseCase,
            checkFavoriteUseCase,
            addFavoriteUseCase,
            removeFavoriteUseCase,
            mainCoroutineRule.testDispatcherProvider
        ).apply {
            categories.state.observeForever(uiStateObserver)
            favorite.state.observeForever(favoriteUiStateObserver)
        }
    }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns success`() =
        runTest {
            //Arrange
            whenever(
                getCharacterCategoriesUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    ResultStatus.Success(
                        comics to events
                    )
                )
            )

            //Act
            detailViewModel.categories.load(character.id)

            //Assert
            verify(uiStateObserver).onChanged(isA<UiActionStateLiveData.UiState.Success>())

            val uiStateSuccess =
                detailViewModel.categories.state.value as UiActionStateLiveData.UiState.Success
            val categoriesParentList = uiStateSuccess.detailParentList

            assertEquals(2, categoriesParentList.size)
            assertEquals(
                R.string.details_comics_category,
                categoriesParentList[0].categoryStringResId
            )
            assertEquals(
                R.string.details_events_category,
                categoriesParentList[1].categoryStringResId
            )
        }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns only comics`() =
        runTest {
            whenever(
                getCharacterCategoriesUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    ResultStatus.Success(
                        comics to emptyList()
                    )
                )
            )

            detailViewModel.categories.load(character.id)

            verify(uiStateObserver).onChanged(isA<UiActionStateLiveData.UiState.Success>())

            val uiStateSuccess =
                detailViewModel.categories.state.value as UiActionStateLiveData.UiState.Success
            val categoriesParentList = uiStateSuccess.detailParentList

            assertEquals(1, categoriesParentList.size)
            assertEquals(
                R.string.details_comics_category,
                categoriesParentList[0].categoryStringResId
            )
        }

    @Test
    fun `should notify uiState with Success from UiState when get character categories returns only events`() =
        runTest {
            whenever(
                getCharacterCategoriesUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    ResultStatus.Success(
                        emptyList<Comic>() to events
                    )
                )
            )

            detailViewModel.categories.load(character.id)

            verify(uiStateObserver).onChanged(isA<UiActionStateLiveData.UiState.Success>())

            val uiStateSuccess =
                detailViewModel.categories.state.value as UiActionStateLiveData.UiState.Success
            val categoriesParentList = uiStateSuccess.detailParentList

            assertEquals(1, categoriesParentList.size)
            assertEquals(
                R.string.details_events_category,
                categoriesParentList[0].categoryStringResId
            )
        }

    @Test
    fun `should notify uiState with Empty from UiState when get character categories returns an empty result list`() =
        runTest {

            whenever(
                getCharacterCategoriesUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    ResultStatus.Success(
                        emptyList<Comic>() to emptyList()
                    )
                )
            )

            detailViewModel.categories.load(character.id)

            verify(uiStateObserver).onChanged(isA<UiActionStateLiveData.UiState.Empty>())

        }

    @Test
    fun `should notify uiState with Error from UiState when get character categories returns an exception`() =
        runTest {

            whenever(
                getCharacterCategoriesUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    ResultStatus.Error(
                        Throwable()
                    )
                )
            )

            detailViewModel.categories.load(character.id)

            verify(uiStateObserver).onChanged(isA<UiActionStateLiveData.UiState.Error>())

        }

    @Test
    fun `should notify favorite_uiState with filled favorite icon when check favorite returns true`() =
        runTest {
            whenever(
                checkFavoriteUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    ResultStatus.Success(true)
                )
            )

            detailViewModel.favorite.checkFavorite(character.id)

            verify(favoriteUiStateObserver).onChanged(isA<FavoriteUiActionStateLiveData.UiState.Icon>())

            val uiState =
                detailViewModel.favorite.state.value as FavoriteUiActionStateLiveData.UiState.Icon

            assertEquals(R.drawable.ic_favorite_checked, uiState.icon)
        }

    @Test
    fun `should notify favorite_uiState with not filled favorite icon when check favorite returns false`() =
        runTest {
            whenever(
                checkFavoriteUseCase.invoke(any())
            ).thenReturn(
                flowOf(
                    ResultStatus.Success(false)
                )
            )

            detailViewModel.favorite.checkFavorite(character.id)

            verify(favoriteUiStateObserver).onChanged(isA<FavoriteUiActionStateLiveData.UiState.Icon>())

            val uiState =
                detailViewModel.favorite.state.value as FavoriteUiActionStateLiveData.UiState.Icon

            assertEquals(R.drawable.ic_favorite_unchecked, uiState.icon)
        }

}