package com.example.tablesmanagement.view.ui


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tablesmanagement.view.screens.HomeScreen
import com.example.tablesmanagement.view.screens.MapScreen
import com.example.tablesmanagement.viewModel.TablesViewModel

@Composable
fun AppNavigator(
    navController: NavHostController,
    tablesViewModel: TablesViewModel
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("map") {

            MapScreen(navController = navController, tablesViewModel = tablesViewModel)
        }
    }
}