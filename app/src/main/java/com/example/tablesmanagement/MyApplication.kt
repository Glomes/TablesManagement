package com.example.tablesmanagement


import android.app.Application
import com.example.tablesmanagement.data.local.AppDatabase
import com.example.tablesmanagement.data.local.dao.CheckPadDao
import kotlin.getValue

class MyApplication : Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    val checkPadDao: CheckPadDao by lazy { database.checkPadDao() }
}