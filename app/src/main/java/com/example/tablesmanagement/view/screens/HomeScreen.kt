package com.example.tablesmanagement.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tablesmanagement.R
import com.example.tablesmanagement.ui.theme.PoppinsFontFamily
import com.example.tablesmanagement.view.components.ActionCard
import com.example.tablesmanagement.view.components.BottomMenu
import com.example.tablesmanagement.view.components.Header


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0))
    ) {

        Header()
        HorizontalDivider(
            thickness = 2.dp,
            color = Color(0x0F383737),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp)

        ) {
            Text(
                text = "Lucas Gomes",
                style = MaterialTheme.typography.titleLarge,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Croc Restaurante",
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ActionCard(
                    text = "Novo pedido",
                    iconRes = R.drawable.add,
                    onClick = { showBottomSheet = true },
                    modifier = Modifier.weight(1f)

                )

                ActionCard(
                    text = "Mapa de atendimento",
                    iconRes = R.drawable.cards,
                    onClick = { navController.navigate("map") },
                    modifier = Modifier.weight(1f)
                )
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = { showBottomSheet = false }
                ) {
                    BottomMenu()
                }
            }
        }
    }
}