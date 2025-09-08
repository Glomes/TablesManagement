package com.example.tablesmanagement.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tablesmanagement.data.local.dao.CheckPadDao

class TablesViewModelFactory(
    private val application: Application,
    private val checkPadDao: CheckPadDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TablesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TablesViewModel(application, checkPadDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}