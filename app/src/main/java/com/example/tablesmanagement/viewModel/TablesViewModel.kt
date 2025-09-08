package com.example.tablesmanagement.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tablesmanagement.R
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

class TablesViewModel(application: Application) : AndroidViewModel(application) {
    private val _allCheckPads = MutableStateFlow<List<CheckPads>>(emptyList())
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
        _allCheckPads,
        _selectedFilter,
        debouncedSearchQuery
    ) { allCheckPads, filter, query ->

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
        loadTablesData()
    }

    private fun loadTablesData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val context = getApplication<Application>().applicationContext
                val inputStream = context.resources.openRawResource(R.raw.data)
                val jsonContent = InputStreamReader(inputStream).use { it.readText() }
                val jsonParser = Json { ignoreUnknownKeys = true }
                val tables = jsonParser.decodeFromString<Tables>(jsonContent)

                _allCheckPads.value = tables.checkPads
            } catch (e: Exception) {
                e.printStackTrace()
                _allCheckPads.value = emptyList()
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