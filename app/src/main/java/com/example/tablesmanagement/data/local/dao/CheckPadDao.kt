package com.example.tablesmanagement.data.local.dao

import CheckPadEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface CheckPadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(checkPads: List<CheckPadEntity>)

    @Query("SELECT * FROM check_pads ORDER BY title ASC")
    fun getAllCheckPads(): Flow<List<CheckPadEntity>>

    @Query("SELECT * FROM check_pads WHERE activity = :activity")
    fun getCheckPadsByActivity(activity: String): Flow<List<CheckPadEntity>>

    @Query("SELECT COUNT(*) FROM check_pads")
    suspend fun countCheckPads(): Int
}