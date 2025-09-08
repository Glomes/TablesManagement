package com.example.tablesmanagement.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tablesmanagement.model.CheckPads
import com.example.tablesmanagement.viewModel.TablesViewModel

@Composable
fun TableCardsList(viewModel: TablesViewModel, onCardClick: (CheckPads) -> Unit) {

    val checkPadsList = viewModel.checkPadsList.collectAsState()
    val selectedFilter = viewModel.selectedFilter.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column {


        FilterChips(
            selected = selectedFilter.value,
            onSelected = { filter ->
                viewModel.updateSelectedFilter(filter)
            }
        )
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            LazyVerticalGrid(

                columns = GridCells.Adaptive(minSize = 120.dp),
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 64.dp)
            ) {
                items(checkPadsList.value, key = { it.id }) { checkPad ->
                    TableCard(checkPad = checkPad, onCardClick = onCardClick)
                }
            }
        }
    }

}