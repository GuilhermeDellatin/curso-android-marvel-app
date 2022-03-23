package com.gfdellatin.curso_android_marvel_app.presentation.characters

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gfdellatin.curso_android_marvel_app.R
import com.gfdellatin.curso_android_marvel_app.extension.asJsonString
import com.gfdellatin.curso_android_marvel_app.framework.di.BaseUrlModule
import com.gfdellatin.curso_android_marvel_app.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@UninstallModules(BaseUrlModule::class)
@HiltAndroidTest
class CharactersFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer().apply {
            start(8080)
        }
        launchFragmentInHiltContainer<CharactersFragment>()
    }

    @Test
    fun shouldShowCharacters_whenViewIsCreated() {
        server.enqueue(MockResponse().setBody("characters_p1.json".asJsonString()))
        onView(
            withId(R.id.recycler_characters)
        ).check(
            matches(isDisplayed())
        )
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

}