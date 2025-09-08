package com.example.tablesmanagement.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.tablesmanagement.model.CheckPads
import com.example.tablesmanagement.ui.theme.Orange
import com.example.tablesmanagement.ui.theme.PoppinsFontFamily
import com.example.tablesmanagement.view.screens.ui.formatIdleTime

@Composable
fun DetailPopUp(checkPad: CheckPads, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Detalhes da ${checkPad.title}",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))
                checkPad.orderSheets.firstOrNull()?.let { order ->
                    DetailRow("Cliente:", order.customerName ?: "Sem nome")
                    DetailRow("Atendente:", order.seller?.name ?: "N/A")
                    DetailRow("Total:", "R$ %.2f".format(order.subTotal / 100.0))
                    DetailRow("Tempo Ocioso:", formatIdleTime(order.idleTime))
                    DetailRow("Numero de Pedidos", checkPad.orderSheets.size.toString())
                } ?: run {
                    Text(
                        text = "Mesa vazia.",
                        style = MaterialTheme.typography.bodyLarge, fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(containerColor = Orange)
                ) {
                    Text("Fechar")
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label, fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value, fontFamily = PoppinsFontFamily,
            fontWeight = FontWeight.Normal
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}