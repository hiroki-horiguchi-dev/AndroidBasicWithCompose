/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.compose.unit4.navigation.practice

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.R
import com.example.compose.ui.theme.WoofTheme
import com.example.compose.unit4.navigation.practice.datasource.NavigationDataSource
import com.example.compose.unit4.navigation.practice.ui.AccompanimentMenuScreen
import com.example.compose.unit4.navigation.practice.ui.CheckoutScreen
import com.example.compose.unit4.navigation.practice.ui.EntreeMenuScreen
import com.example.compose.unit4.navigation.practice.ui.OrderViewModel
import com.example.compose.unit4.navigation.practice.ui.SideDishMenuScreen
import com.example.compose.unit4.navigation.practice.ui.StartOrderScreen

// TODO: Screen enum
enum class LunchTrayScreen(@StringRes val title: Int) {
    START(title = R.string.navigation_home_screen),
    MAIN_MENU(title = R.string.choose_entree),
    SUB_MENU(title = R.string.choose_side_dish),
    OPTION_MENU(title = R.string.choose_accompaniment),
    PURCHASE(title = R.string.order_checkout)
}

// TODO: AppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    currentScreen: LunchTrayScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    // スタックの state を取得
    val backStackEntry by navController.currentBackStackEntryAsState()
    // スタックに登録した route を LunchTrayScreen として currentScreen へ代入
    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.START.name
    )

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                currentScreen = currentScreen,
                // navController の スタックを確認して何かあれば back key を表示するフラグ
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        // TODO: Navigation host
        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.START.name,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            composable(route = LunchTrayScreen.START.name) {
                StartOrderScreen(onStartOrderButtonClicked = {
                    navController.navigate(LunchTrayScreen.MAIN_MENU.name)
                })
            }
            composable(route = LunchTrayScreen.MAIN_MENU.name) {
                EntreeMenuScreen(
                    options = NavigationDataSource.entreeMenuItems,
                    onCancelButtonClicked = { onCanselButtonClicked(viewModel = viewModel, navController = navController) },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.SUB_MENU.name)
                    },
                    onSelectionChanged = {
                        /// State の変更は選択された時にしろってメッセージを感じたぞ。
                        viewModel.updateEntree(it)
                    }
                )
            }
            composable(route = LunchTrayScreen.SUB_MENU.name) {
                SideDishMenuScreen(
                    options = NavigationDataSource.sideDishMenuItems,
                    onCancelButtonClicked = { onCanselButtonClicked(viewModel = viewModel, navController = navController) },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.OPTION_MENU.name)
                    },
                    onSelectionChanged = { sideDishItem ->
                        viewModel.updateSideDish(sideDishItem)
                    }
                )
            }
            composable(route = LunchTrayScreen.OPTION_MENU.name) {
                AccompanimentMenuScreen(
                    options = NavigationDataSource.accompanimentMenuItems,
                    onCancelButtonClicked = { onCanselButtonClicked(viewModel = viewModel, navController = navController) },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.PURCHASE.name)
                    },
                    onSelectionChanged = { accompanimentItem ->
                        viewModel.updateAccompaniment(accompanimentItem)
                    }
                )
            }
            composable(route = LunchTrayScreen.PURCHASE.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.START.name)
                    },
                    onCancelButtonClicked = { onCanselButtonClicked(viewModel = viewModel, navController = navController) },
                )
            }

        }
    }
}

private fun onCanselButtonClicked(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(LunchTrayScreen.START.name, inclusive = false)
}

@Preview(showBackground = true)
@Composable
fun `ホーム画面のプレビュー`() {
    LunchTrayAppBar(
        currentScreen = LunchTrayScreen.START,
        canNavigateBack = false,
        navigateUp = {}
    )
}

@Preview(showBackground = true)
@Composable
fun `メインメニューのプレビュー`() {
    LunchTrayAppBar(
        currentScreen = LunchTrayScreen.MAIN_MENU,
        canNavigateBack = true,
        navigateUp = {}
    )
}

@Preview(showBackground = true)
@Composable
fun LunchTrayScreenPreview() {
    WoofTheme {
        LunchTrayApp()
    }
}