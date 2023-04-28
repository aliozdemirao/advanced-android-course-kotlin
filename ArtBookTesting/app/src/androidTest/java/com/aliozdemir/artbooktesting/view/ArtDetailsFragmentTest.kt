package com.aliozdemir.artbooktesting.view

import com.aliozdemir.artbooktesting.R
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.aliozdemir.artbooktesting.getOrAwaitValue
import com.aliozdemir.artbooktesting.launchFragmentInHiltContainer
import com.aliozdemir.artbooktesting.repo.FakeArtRepositoryTest
import com.aliozdemir.artbooktesting.roomdb.Art
import com.aliozdemir.artbooktesting.viewmodel.ArtViewModel
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
class ArtDetailsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory : ArtFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromArtDetailsToImageAPI() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtDetailsFragment>(factory = fragmentFactory) {
            Navigation.setViewNavController(requireView(),navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.artImageView)).perform(ViewActions.click())

        Mockito.verify(navController).navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())

    }

    @Test
    fun testOnBackPressed() {
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<ArtDetailsFragment>(factory = fragmentFactory) {
            Navigation.setViewNavController(requireView(),navController)
        }

        Espresso.pressBack()
        Mockito.verify(navController).popBackStack()
    }

    @Test
    fun testSave() {
        val testViewModel = ArtViewModel(FakeArtRepositoryTest())
        launchFragmentInHiltContainer<ArtDetailsFragment>(factory = fragmentFactory) {
            viewModel = testViewModel
        }

        Espresso.onView(ViewMatchers.withId(R.id.nameText)).perform(ViewActions.replaceText("Mona Lisa"))
        Espresso.onView(ViewMatchers.withId(R.id.artistText)).perform(ViewActions.replaceText("Da Vinci"))
        Espresso.onView(ViewMatchers.withId(R.id.yearText)).perform(ViewActions.replaceText("1700"))
        Espresso.onView(ViewMatchers.withId(R.id.saveButton)).perform(ViewActions.click())

        assertThat(testViewModel.artList.getOrAwaitValue()).contains(
            Art("Mona Lisa", "Da Vinci", 1700,"")
        )

    }

}