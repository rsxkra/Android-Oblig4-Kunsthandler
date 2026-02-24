package com.example.artshop
/*
import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.artshop.models.Basket.clearBasket
import com.example.artshop.navigation.NavGraph
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NavGraph(navController = navController, orientation = 2)
        }
        clearBasket()
    }

    @Test
    fun test_home_to_artists_to_checkout() {
        composeTestRule.onNodeWithText("Artists").performClick()

        composeTestRule.onNodeWithText("Pablo Picasso").performClick()

        composeTestRule.onNodeWithContentDescription("Ocean").performClick()

        composeTestRule.onNodeWithText("Add to basket").performClick()

        composeTestRule.onNodeWithText("Checkout").performClick()

        composeTestRule.onNodeWithText("Pay").assertExists()
    }


    @Test
    fun test_home_to_categories_to_checkout() {
        composeTestRule.onNodeWithText("Categories").performClick()

        composeTestRule.onNodeWithText("Abstract").performClick()

        composeTestRule.onNodeWithContentDescription("Grass").performClick()

        composeTestRule.onNodeWithText("Add to basket").performClick()

        composeTestRule.onNodeWithText("Checkout").performClick()

        composeTestRule.onNodeWithText("Pay").assertExists()
    }

    @Test
    fun test_add_and_remove_photo() {

        composeTestRule.onNodeWithText("Artists").performClick()

        composeTestRule.onNodeWithText("Vincent van Gogh").performClick()

        composeTestRule.onNodeWithContentDescription("Beach").performClick()

        composeTestRule.onNodeWithText("Add to basket").performClick()

        val expectedTextOne = ApplicationProvider.getApplicationContext<Context>().getString(R.string.TotalPics) + " 1"
        composeTestRule.onNodeWithText(expectedTextOne).assertExists()

        composeTestRule.onNodeWithContentDescription("Delete").performClick()

        val expectedTextZero = ApplicationProvider.getApplicationContext<Context>().getString(R.string.TotalPics) + " 0"
        composeTestRule.onNodeWithText(expectedTextZero).assertExists()
    }
}

 */