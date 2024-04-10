package com.example.compose.unit4.navigation.practice

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.compose.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LunchTrayAppKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    /// テスト用　Navhost　の準備
    @Before
    fun `Navhost準備`() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            LunchTrayApp(navController = navController)
        }
    }

    @Test
    fun `最初の画面がSTART`() {
        navController.assertCurrentRouteName(LunchTrayScreen.START.name)
    }

    @Test
    fun `START画面でSTARTORDERボタンタップ時、MAIN_MENU画面へ遷移する`() {
        composeTestRule.onNodeWithStringId(R.string.start_order).performClick()
        navController.assertCurrentRouteName(LunchTrayScreen.MAIN_MENU.name)
    }




    // TODO 実際に書く時の注意点: 拡張関数なので別のファイルに分けましょう！
    // この辺参考にしてね: https://github.com/google-developer-training/basic-android-kotlin-compose-training-cupcake/blob/main/app/src/androidTest/java/com/example/cupcake/test/ComposeRuleExtensions.kt
    // 拡張関数
    private fun NavController.assertCurrentRouteName(expectedRouteName: String) {
        assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
    }

    // Text がついているボタン全般に使えるので非常に良い！
    private fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
        @StringRes id: Int
    ): SemanticsNodeInteraction = onNodeWithText(activity.getString(id),ignoreCase = true)
}