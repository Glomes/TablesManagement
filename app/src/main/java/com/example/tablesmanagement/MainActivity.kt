package com.example.tablesmanagement


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.tablesmanagement.ui.theme.TablesManagementTheme
import com.example.tablesmanagement.view.ui.AppNavigator
import com.example.tablesmanagement.viewModel.TablesViewModel
import com.example.tablesmanagement.viewModel.TablesViewModelFactory

class MainActivity : ComponentActivity() {
    private val tablesViewModel: TablesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val application = application as MyApplication
        val checkPadDao = application.checkPadDao

        val tablesViewModel: TablesViewModel by viewModels {
            TablesViewModelFactory(application, checkPadDao)
        }

        setContent {

            TablesManagementTheme() {
                val viewModel = remember { tablesViewModel }
                val navController = rememberNavController()

                AppNavigator(navController, viewModel)
            }
        }
    }
}

