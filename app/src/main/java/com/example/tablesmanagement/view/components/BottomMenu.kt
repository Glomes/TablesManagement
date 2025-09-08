package com.example.tablesmanagement.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tablesmanagement.R
import com.example.tablesmanagement.ui.theme.PoppinsFontFamily

@Composable
fun BottomMenu() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Novo pedido",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Selecione o tipo de pedido",
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(16.dp))

        BottomSheetItem(
            text = "Mesa/Comanda",
            iconRes = R.drawable.table_restaurant,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(8.dp))

        BottomSheetItem(
            text = "Balcão",
            iconRes = R.drawable.shopping_bag_speed,
            onClick = {}
        )

    }
}