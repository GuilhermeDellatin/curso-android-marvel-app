package com.gfdellatin.core.usecase

import com.gfdellatin.core.data.repository.CharactersRepository
import com.gfdellatin.testing.MainCoroutineRule
import com.gfdellatin.testing.model.ComicFactory
import com.gfdellatin.testing.model.EventFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharacterCategoriesUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: CharactersRepository

    private val comics = listOf(ComicFactory().create(ComicFactory.FakeComic.FakeComic1))
    private val events = listOf(EventFactory().create(EventFactory.FakeEvent.FakeEvent1))

    private lateinit var getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase

    @Before
    fun setUp() {
        getCharacterCategoriesUseCase = GetCharacterCategoriesUseCaseImpl(
            repository,
            mainCoroutineRule.testDispatcherProvider
        )
    }

}