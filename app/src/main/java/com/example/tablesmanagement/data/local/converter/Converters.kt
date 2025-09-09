package com.example.tablesmanagement.data.local.converter

import androidx.room.TypeConverter
import com.example.tablesmanagement.model.Customer
import com.example.tablesmanagement.model.CustomerAccount
import com.example.tablesmanagement.model.OrderSheet
import com.example.tablesmanagement.model.PdvDevice
import com.example.tablesmanagement.model.Seller
import com.example.tablesmanagement.model.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class Converters {

    private val json = Json { ignoreUnknownKeys = true }


    @TypeConverter
    fun fromPdvDeviceList(value: List<PdvDevice>): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toPdvDeviceList(value: String): List<PdvDevice> {
        return json.decodeFromString(value)
    }


    @TypeConverter
    fun fromOrderSheetList(value: List<OrderSheet>): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toOrderSheetList(value: String): List<OrderSheet> {
        return json.decodeFromString(value)
    }

}