package com.example.tablesmanagement.view.components

import FilterChips
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.tablesmanagement.model.CheckPads
import com.example.tablesmanagement.viewModel.TablesViewModel
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


@Composable
fun TableCardsList(viewModel: TablesViewModel, onCardClick: (CheckPads) -> Unit) {
    val checkPads = viewModel.tablesPagingFlow.collectAsLazyPagingItems()
    val selectedFilter by viewModel.selectedFilter.collectAsState()

    FilterChips(
        selectedFilter = selectedFilter,
        onFilterSelected = { filter ->
            viewModel.updateSelectedFilter(filter)
        }
    )

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 64.dp)
    ) {

        PagingGridItems(
            items = checkPads,
            key = { it.id }
        ) { checkPad ->
            checkPad?.let {
                TableCard(checkPad = it, onCardClick = onCardClick)
            }
        }



        when {
            checkPads.loadState.refresh is LoadState.Loading -> {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            checkPads.loadState.append is LoadState.Loading -> {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            checkPads.loadState.refresh is LoadState.Error -> {
                val error = checkPads.loadState.refresh as LoadState.Error
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Erro: ${error.error.message}",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

            checkPads.loadState.append is LoadState.Error -> {
                val error = checkPads.loadState.append as LoadState.Error
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Erro ao carregar mais dados: ${error.error.message}",
                        )
                    }
                }
            }
        }
    }
}