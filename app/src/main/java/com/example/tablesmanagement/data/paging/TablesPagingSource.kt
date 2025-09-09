package com.example.tablesmanagement.data.paging

import CheckPadEntity
import android.util.Log
import com.example.tablesmanagement.data.local.dao.CheckPadDao
import androidx.paging.PagingSource
import androidx.paging.PagingState

class TablesPagingSource(
    private val checkPadDao: CheckPadDao,
    private val searchQuery: String,
    private val filterQuery: String
) : PagingSource<Int, CheckPadEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CheckPadEntity> {
        return try {
            val position = params.key ?: 1
            val pageSize = params.loadSize

            val dbFilter = when (filterQuery.lowercase()) {
                "visão geral" -> ""
                "em atendimento" -> "active"
                "ociosas" -> "inactive"
                "disponíveis" -> "empty"
                "sem pedidos" -> "waiting"
                else -> ""
            }

            val data = checkPadDao.getFilteredCheckPads(
                pageSize = pageSize,
                offset = (position - 1) * pageSize,
                searchQuery = searchQuery,
                filterQuery = dbFilter
            )
            Log.d("Paging", "Carregando página $position. Tamanho da página: $pageSize. Itens encontrados: ${data.size}.")

            LoadResult.Page(
                data = data,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, CheckPadEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}