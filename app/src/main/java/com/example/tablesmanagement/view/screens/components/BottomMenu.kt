package com.example.tablesmanagement.view.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import com.example.tablesmanagement.R

@Composable
fun BottomMenu() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Novo pedido", style = MaterialTheme.typography.titleMedium)
        Text(text = "Selecione o tipo de pedido", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))

        BottomSheetItem (
            text = "Mesa/Comanda",
            iconRes = R.drawable.table_restaurant,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(8.dp))

        BottomSheetItem (
            text = "Balcão",
            iconRes = R.drawable.shopping_bag_speed,
            onClick = {}
        )

    }
}