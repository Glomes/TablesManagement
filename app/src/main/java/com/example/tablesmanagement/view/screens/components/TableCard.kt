package com.example.tablesmanagement.view.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tablesmanagement.R
import com.example.tablesmanagement.model.CheckPads
import com.example.tablesmanagement.ui.theme.Greenlight
import com.example.tablesmanagement.ui.theme.Redlight
import com.example.tablesmanagement.ui.theme.Yellowlight

@Composable
fun TableCard(checkPad: CheckPads) {
    val activityColor = checkPad.activity
    val cardBackgroundColor = when (activityColor) {
        "active" -> Greenlight
        "inactive" -> Redlight
        "waiting" -> Yellowlight
        else -> Color.White
    }
    Card(
        modifier = Modifier
            .width(110.dp)
            .height(116.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${checkPad.title}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier, verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = "Ícone de cliente",

                    )
                Text("${checkPad.orderSheets.firstOrNull()?.numberOfCustomers}", style = MaterialTheme.typography.bodySmall)
            }
            Row(
                modifier = Modifier, verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.paid),
                    contentDescription = "Ícone de preço",

                    )
                Text("${checkPad.orderSheets.firstOrNull()?.subTotal}",style = MaterialTheme.typography.bodySmall)
            }
            Row(
                modifier = Modifier, verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.schedule),
                    contentDescription = "Ícone do ultimo pedido feito",

                    )
                Text("${checkPad.orderSheets.firstOrNull()?.idleTime} "+ "Min",style = MaterialTheme.typography.bodySmall)
            }
            Row(
                modifier = Modifier, verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.room_service),
                    contentDescription = "Ícone de cliente",

                    )
                Text("${checkPad.orderSheets.firstOrNull()?.seller?.name}",style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

