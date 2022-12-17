package com.example.composecardflipsample.ui.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composecardflipsample.ui.theme.ComposeCardFlipSampleTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FlipCardKtTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ComposeCardFlipSampleTheme{
                FlipCard()
            }
        }
    }

    @Test
    fun initialState_onlyShows_frontCard() {
        composeTestRule.onNodeWithTag("Front", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("Back", useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun tap_front_flip_Back_tap_back_flip_front() {
        composeTestRule.onNodeWithTag("Front", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithTag("Back", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("Front", useUnmergedTree = true).assertDoesNotExist()


        composeTestRule.onNodeWithTag("Back", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithTag("Front", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("Back", useUnmergedTree = true).assertDoesNotExist()
    }

}