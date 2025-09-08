package com.example.tablesmanagement.view.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tablesmanagement.R
import com.example.tablesmanagement.ui.theme.PoppinsFontFamily

@Composable
fun BottomSheetItem(
    text: String,
    iconRes: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0x0F383737), shape = RoundedCornerShape(16.dp))
            .padding(vertical = 16.dp, horizontal = 24.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.weight(0.1f)
        )
        Text(
            text = text,fontFamily = PoppinsFontFamily, fontWeight = FontWeight.Normal,
            modifier = Modifier.weight(0.8f)
        )
        Icon(
            painter = painterResource(id = R.drawable.chevron_right),
            contentDescription = "Ir para",
            modifier = Modifier.weight(0.1f)
        )
    }
}

