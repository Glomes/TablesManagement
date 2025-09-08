package com.example.tablesmanagement.view.screens.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import com.example.tablesmanagement.ui.theme.Greenlight
import com.example.tablesmanagement.ui.theme.Redlight
import com.example.tablesmanagement.ui.theme.Yellowlight

@Composable
fun String.toActivityColor(): Color = when (this) {
    "active" -> Greenlight
    "inactive" -> Redlight
    "waiting" -> Yellowlight
    else -> White
}
fun formatIdleTime(minutes: Int): String {
    return if (minutes < 60) {
        "$minutes min"
    } else {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        if (remainingMinutes == 0) {
            "${hours}h"
        } else {
            "${hours}h ${remainingMinutes}min"
        }
    }
}

