package com.example.tablesmanagement.view.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tablesmanagement.ui.theme.Gray
import com.example.tablesmanagement.ui.theme.Orange
import com.example.tablesmanagement.ui.theme.PoppinsFontFamily
import com.example.tablesmanagement.view.screens.components.FilterChips
import com.example.tablesmanagement.view.screens.components.FindBar
import com.example.tablesmanagement.view.screens.components.TableCardsList
import com.example.tablesmanagement.viewModel.TablesViewModel


@Composable
fun MapScreen(navController: NavController, tablesViewModel: TablesViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { navController.popBackStack() }) {

                Icon(
                    tint = Orange,
                    painter = painterResource(id = com.example.tablesmanagement.R.drawable.vector),
                    contentDescription = "Voltar",
                    )

            }


            Text(
                text = "Mapa de atendimento",
                style = MaterialTheme.typography.titleLarge,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold
            )

        }
        HorizontalDivider(
            thickness = 2.dp,
            color = Gray,
        )

        FindBar(modifier = Modifier.padding(8.dp), viewModel = tablesViewModel)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray)
                .padding(16.dp).padding(bottom = 24.dp),
        ) {

            TableCardsList(tablesViewModel)

        }
    }
}