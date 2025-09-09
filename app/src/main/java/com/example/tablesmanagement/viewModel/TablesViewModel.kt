package com.example.tablesmanagement.viewModel

import CheckPadEntity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.tablesmanagement.R
import com.example.tablesmanagement.data.local.dao.CheckPadDao
import com.example.tablesmanagement.data.paging.TablesPagingSource
import com.example.tablesmanagement.model.CheckPads
import com.example.tablesmanagement.model.Tables
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.io.InputStreamReader
import kotlinx.coroutines.flow.combine
import androidx.paging.PagingData
import kotlin.String

class TablesViewModel(application: Application, private val checkPadDao: CheckPadDao) :
    AndroidViewModel(application) {
    private val _selectedFilter = MutableStateFlow("Visão Geral")
    val selectedFilter: StateFlow<String> = _selectedFilter

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _isLoading = MutableStateFlow(true)


    private val _queryAndFilter = combine(_searchQuery, _selectedFilter) { query, filter ->
        Pair(query, filter)
    }

    val tablesPagingFlow: StateFlow<PagingData<CheckPads>> = _queryAndFilter
        .debounce(300L)
        .flatMapLatest { (query, filter) ->
            val pageSize = 20
            Pager(
                config = PagingConfig(
                    pageSize = pageSize,
                    enablePlaceholders = false
                )
            ) {
                TablesPagingSource(
                    checkPadDao = checkPadDao,
                    searchQuery = query,
                    filterQuery = filter,
                    pageSize =  pageSize
                )
            }.flow
        }
        .map { pagingData ->

            pagingData.map { entity ->
                CheckPads(
                    id = entity.id,
                    status = entity.status,
                    hash = entity.hash,
                    title = entity.title,
                    hasPdv = entity.hasPdv,
                    lastOrderCreated = entity.lastOrderCreated,
                    hasOrderSheets = entity.hasOrderSheets,
                    hasOrder = entity.hasOrder,
                    idleTime = entity.idleTime,
                    activity = entity.activity,
                    pdvDevices = entity.pdvDevices,
                    orderSheets = entity.orderSheets
                )
            }
        }
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        )

    init {
        loadAndStoreTablesData()
    }

    private fun loadAndStoreTablesData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                if (checkPadDao.countCheckPads() == 0) {
                    val context = getApplication<Application>().applicationContext
                    val inputStream = context.resources.openRawResource(R.raw.data)
                    val jsonContent = InputStreamReader(inputStream).use { it.readText() }
                    val jsonParser = Json { ignoreUnknownKeys = true }
                    val tables = jsonParser.decodeFromString<Tables>(jsonContent)

                    val checkPadEntities = tables.checkPads.map { checkPad ->
                        CheckPadEntity(
                            id = checkPad.id,
                            status = checkPad.status,
                            hash = checkPad.hash,
                            title = checkPad.title,
                            hasPdv = checkPad.hasPdv,
                            customerName = checkPad.orderSheets.firstOrNull()?.customerName ?: "",
                            sellerName = checkPad.orderSheets.firstOrNull()?.seller?.name ?: "",
                            lastOrderCreated = checkPad.lastOrderCreated,
                            hasOrderSheets = checkPad.hasOrderSheets,
                            hasOrder = checkPad.hasOrder,
                            idleTime = checkPad.idleTime,
                            activity = checkPad.activity,
                            pdvDevices = checkPad.pdvDevices,
                            orderSheets = checkPad.orderSheets
                        )
                    }
                    checkPadDao.insertAll(checkPadEntities)
                }

            } catch (e: Exception) {
                e.printStackTrace()

            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateSelectedFilter(filter: String) {
        _selectedFilter.value = filter
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}