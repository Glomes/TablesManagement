package com.example.tablesmanagement.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tablesmanagement.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import com.example.tablesmanagement.view.screens.components.ActionCard
import com.example.tablesmanagement.view.screens.components.BottomMenu
import com.example.tablesmanagement.view.screens.components.Header
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0))
    ) {

        Header()
        HorizontalDivider(thickness = 2.dp,
            color = Color(0x0F383737),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)
        ){
            Text(text = "Lucas Gomes", style = MaterialTheme.typography.titleLarge)
            Text(text = "Croc Restaurante")

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ActionCard(
                    text = "Novo pedido",
                    iconRes = R.drawable.add,

                    onClick = { showBottomSheet = true}

                )

                ActionCard(text = "Mapa de atendimento",
                    iconRes = R.drawable.cards,
                    onClick = { navController.navigate("Map")}
                    )
            }

        if (showBottomSheet){
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false}
            ) {
               BottomMenu()
            }
        }
        }

    }
}