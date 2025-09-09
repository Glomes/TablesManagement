package com.example.tablesmanagement.view.components

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems

fun <T : Any> LazyGridScope.PagingGridItems(
    items: LazyPagingItems<T>,
    key: ((item: T) -> Any)? = null,
    itemContent: @Composable (T?) -> Unit
) {
    items(
        count = items.itemCount,
        key = if (key != null) { index ->
            items[index]?.let { key(it) } ?: index
        } else { index -> index }
    ) { index ->
        itemContent(items[index])
    }
}

