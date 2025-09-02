package com.example.tablesmanagement.view.screens.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Card
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.clickable

@Composable
fun ActionCard(text: String, iconRes: Int,modifier: Modifier = Modifier,  onClick: () -> Unit){
    Card(
        modifier = modifier
            .width(156.dp)
            .height(144.dp)
            .background(Color(0xFFF4F2F2))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),

    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center

        ) {


            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
            )
            Spacer(Modifier.height(32.dp))
            Text(text = text, style = MaterialTheme.typography.bodySmall)
        }
    }
}



