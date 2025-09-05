package com.example.tablesmanagement.view.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FilterChips(
    selected: String,
    onSelected: (String) -> Unit
) {
    val filters = listOf("Visão geral", "Em atendimento", "Ociosas", "Disponíveis", "Sem Pedidos")

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(filters) { filter ->
            val isSelected = filter == selected
            Button(
                onClick = { onSelected(filter) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Color.Black else Color.White,
                    contentColor = if (isSelected) Color.White else Color.Black
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text(filter)
            }
        }
    }
}
