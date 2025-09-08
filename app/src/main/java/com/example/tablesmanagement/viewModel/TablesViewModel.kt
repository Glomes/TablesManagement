package com.example.tablesmanagement.viewModel

import CheckPadEntity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tablesmanagement.R
import com.example.tablesmanagement.data.local.dao.CheckPadDao
import com.example.tablesmanagement.model.CheckPads
import com.example.tablesmanagement.model.Tables
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.io.InputStreamReader
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn

class TablesViewModel(application: Application, private val checkPadDao: CheckPadDao) : AndroidViewModel(application) {
    private val _selectedFilter = MutableStateFlow("Visão Geral")

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val selectedFilter: StateFlow<String> = _selectedFilter

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    val debouncedSearchQuery: StateFlow<String> = _searchQuery
        .debounce(300L)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )
    val checkPadsList: StateFlow<List<CheckPads>> = combine(
        checkPadDao.getAllCheckPads(),
        _selectedFilter,
        debouncedSearchQuery
    ) { allCheckPadsFromDb, filter, query ->

        val allCheckPads = allCheckPadsFromDb.map { entity ->
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

        val filteredByStatus = when (filter) {
            "Em atendimento" -> allCheckPads.filter { it.activity == "active" }
            "Ociosas" -> allCheckPads.filter { it.activity == "inactive" }
            "Disponíveis" -> allCheckPads.filter { it.activity == "empty" }
            "Sem Pedidos" -> allCheckPads.filter { it.activity == "waiting" }
            else -> allCheckPads
        }

        if (query.isBlank()) {
            filteredByStatus
        } else {
            val lowerCaseQuery = query.trim().lowercase()
            filteredByStatus.filter { checkPad ->
                val order = checkPad.orderSheets.firstOrNull()
                val matchesTable = checkPad.title.toString().lowercase().contains(lowerCaseQuery)
                val matchesCustomer =
                    order?.customerName?.lowercase()?.contains(lowerCaseQuery) ?: false
                val matchesSeller =
                    order?.seller?.name?.lowercase()?.contains(lowerCaseQuery) ?: false

                matchesTable || matchesCustomer || matchesSeller
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )


    init {
        loadAndStoreTablesData()
    }

    private fun loadAndStoreTablesData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
               if( checkPadDao.countCheckPads() == 0){
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