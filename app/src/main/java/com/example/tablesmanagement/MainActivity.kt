package com.example.tablesmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tablesmanagement.ui.theme.TablesManagementTheme
import com.example.tablesmanagement.view.screens.HomeScreen
import com.example.tablesmanagement.view.screens.MapScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            TablesManagementTheme() {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main"){
                    composable("main") {
                        HomeScreen(navController = navController)
                    }
                    composable("map") {
                        MapScreen(navController = navController)
                    }

                }


            }



        }
    }
}

