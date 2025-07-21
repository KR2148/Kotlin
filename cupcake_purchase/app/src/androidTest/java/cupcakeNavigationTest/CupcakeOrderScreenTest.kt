package cupcakeNavigationTest

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.cupcake_purchase.R
import com.example.cupcake_purchase.ui.ChooseFlavors
import com.example.cupcake_purchase.ui.SelectOption
import org.junit.Rule
import org.junit.Test

//The user also interacts with each of the app screens. You need to verify what appears on these screens and that actions taken on these screens yield the correct results.

class CupcakeOrderScreenTest{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        // Given list of options
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And subtotal
        val subtotal = "$100"

        // When SelectOptionScreen is loaded
        composeTestRule.setContent {
            SelectOption(subtotal = subtotal, options = flavors)
        }
        // Then all the options are displayed on the screen.
        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }
        // And then the subtotal is displayed correctly.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subtotal
            )
        ).assertIsDisplayed()


        // And then the next button is disabled
        // coz Next button is not enabled untill u choose flavor
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }
}