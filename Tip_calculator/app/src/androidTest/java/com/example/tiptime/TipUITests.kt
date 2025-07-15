package com.example.tiptime
import com.example.tiptime.ui.theme.TipTimeTheme
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import java.text.NumberFormat

//In the case of the Tip Time app tests, you proceed to write instructions to interact with the UI components so that the tip calculating process is tested through the UI.
class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {  // This sets the UI content of the composeTestRule
            TipTimeTheme {
                TipTimeLayout()
            }
        }
        //UI components can be accessed as nodes through the composeTestRule. A common way to do this is to access a node that contains a particular text with the onNodeWithText() method.
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")  //Populate the TextField for the bill amount with a 10 value:
        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        //        //In instrumentation tests with Compose, assertions can be called directly on UI components.
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
            "No node with this text was found."
        )
    }
}

