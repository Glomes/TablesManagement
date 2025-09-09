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

    @Query(
        """
    SELECT * FROM check_pads 
    WHERE (:searchQuery = '' 
        OR title LIKE '%' || :searchQuery || '%'
        OR customerName LIKE '%' || :searchQuery || '%' 
        OR sellerName LIKE '%' || :searchQuery || '%'
    ) 
    AND (:filterQuery = '' OR LOWER(activity) = :filterQuery)
    ORDER BY title ASC
    LIMIT :pageSize OFFSET :offset
"""
    )
    suspend fun getFilteredCheckPads(
        pageSize: Int,
        offset: Int,
        searchQuery: String,
        filterQuery: String
    ): List<CheckPadEntity>
}


