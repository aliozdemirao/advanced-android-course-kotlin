package com.aliozdemir.artbooktesting.view

import com.aliozdemir.artbooktesting.R
import com.aliozdemir.artbooktesting.adapter.ImageRecyclerAdapter
import com.aliozdemir.artbooktesting.getOrAwaitValue
import com.aliozdemir.artbooktesting.launchFragmentInHiltContainer
import com.aliozdemir.artbooktesting.repo.FakeArtRepositoryTest
import com.aliozdemir.artbooktesting.viewmodel.ArtViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ImageApiFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory : ArtFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testSelectImage() {
        val navController = Mockito.mock(NavController::class.java)
        val selectedImageUrl = "x.com"
        val testViewModel = ArtViewModel(FakeArtRepositoryTest())

        launchFragmentInHiltContainer<ImageApiFragment>(factory = fragmentFactory) {
            Navigation.setViewNavController(requireView(),navController)
            imageRecyclerAdapter.images = listOf(selectedImageUrl)
            viewModel = testViewModel
        }

        Espresso.onView(ViewMatchers.withId(R.id.imageRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageRecyclerAdapter.ImageViewHolder>(
                0,click()
            )
        )

        Mockito.verify(navController).popBackStack()

        assertThat(testViewModel.selectedImageUrl.getOrAwaitValue()).isEqualTo(selectedImageUrl)

    }

}