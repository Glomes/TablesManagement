package com.example.tablesmanagement.view.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.tablesmanagement.R
import com.example.tablesmanagement.model.CheckPads
import com.example.tablesmanagement.model.Tables
import kotlinx.serialization.json.Json
import java.io.InputStreamReader

@Composable
fun TableCardsList() {
    val context = LocalContext.current
    val resourceId = R.raw.data
    val jsonParser = Json { ignoreUnknownKeys = true }

    val checkPadsList: List<CheckPads> = remember {
        try {
            val inputStream = context.resources.openRawResource(resourceId)
            val jsonContent = InputStreamReader(inputStream).use { it.readText() }

            val tables = jsonParser.decodeFromString<Tables>(jsonContent)
            tables.checkPads

        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 110.dp),
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(checkPadsList) { checkPad ->
            TableCard(checkPad = checkPad)
        }
    }
}
