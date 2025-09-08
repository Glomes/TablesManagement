package com.example.tablesmanagement.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.RoomService
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tablesmanagement.model.CheckPads
import com.example.tablesmanagement.ui.theme.PoppinsFontFamily
import com.example.tablesmanagement.view.screens.ui.formatIdleTime
import com.example.tablesmanagement.view.screens.ui.toActivityColor
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TableCard(checkPad: CheckPads, onCardClick: (CheckPads) -> Unit) {

    val cardBackgroundColor = checkPad.activity.toActivityColor()
    val order = checkPad.orderSheets.firstOrNull()
    val textColor = Color.Black
    val formatter = remember {
        NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"))
    }
    Card(
        modifier = Modifier
            .width(110.dp)
            .height(116.dp)
            .clickable{ onCardClick(checkPad)},
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                text = "${checkPad.title}",
                style = MaterialTheme.typography.titleLarge,
                color = textColor,
                fontFamily = PoppinsFontFamily,
                fontWeight = FontWeight.Bold
            )
            if (order != null) {
                Row(
                    modifier = Modifier, verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Ícone de cliente",
                        tint = Color.Black
                    )
                    Spacer(Modifier.width(3.dp))
                    Text(
                        "${order.numberOfCustomers}",
                        style = MaterialTheme.typography.bodySmall,
                        color = textColor,
                        fontFamily = PoppinsFontFamily,
                    )
                }
                Row(
                    modifier = Modifier, verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Icons.Default.MonetizationOn,
                        contentDescription = "Ícone de preço",
                        tint = Color.Black
                    )
                    Spacer(Modifier.width(3.dp))
                    Text(
                        text = formatter.format(order.subTotal / 100.0),
                        style = MaterialTheme.typography.bodySmall,
                        color = textColor,
                        fontFamily = PoppinsFontFamily,

                        )
                }
                Row(
                    modifier = Modifier, verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Ícone do ultimo pedido feito",
                        tint = Color.Black
                    )
                    Spacer(Modifier.width(3.dp))
                    Text(
                        text = formatIdleTime(order.idleTime),
                        style = MaterialTheme.typography.bodySmall,
                        color = textColor,
                        fontFamily = PoppinsFontFamily,

                        )
                }
                Row(
                    modifier = Modifier, verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Icons.Default.RoomService,
                        contentDescription = "Ícone de atendente",
                        tint = Color.Black
                    )
                    Spacer(Modifier.width(3.dp))
                    Text(
                        "${order.seller?.name}",
                        style = MaterialTheme.typography.bodySmall,
                        color = textColor,
                        fontFamily = PoppinsFontFamily,
                    )
                }
            }
        }
    }
}

