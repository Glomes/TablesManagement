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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tablesmanagement.ui.theme.Gray
import com.example.tablesmanagement.view.screens.components.FilterChips
import com.example.tablesmanagement.view.screens.components.FindBar
import com.example.tablesmanagement.view.screens.components.TableCardsList


@Composable
fun MapScreen(navController: NavController) {
    var selectedFilter by remember { mutableStateOf("visão Geral") }
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
                    tint = Color(0xFFFF5E1A),
                    painter = painterResource(id = com.example.tablesmanagement.R.drawable.vector),
                    contentDescription = "Voltar",

                    )

            }

            Spacer(Modifier.width(16.dp))

            Text(
                text = "Mapa de atendimento",
                style = MaterialTheme.typography.titleLarge
            )

        }
        HorizontalDivider(
            thickness = 2.dp,
            color = Gray,
        )

        FindBar(modifier = Modifier.padding(8.dp))


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray)
                .padding(16.dp),
        ) {
            FilterChips(
                selected = selectedFilter,
                onSelected = { selectedFilter = it }
            )
            TableCardsList()

        }
    }
}