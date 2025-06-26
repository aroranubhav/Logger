package com.maxi.logger.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.maxi.logger.data.local.model.Debug
import com.maxi.logger.util.Constants.DEBUG_LOGS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
internal interface DebugDao {

    @Insert
    suspend fun insertLog(log: Debug)

    @Query("SELECT * FROM $DEBUG_LOGS_TABLE")
    fun getLogs(): Flow<List<Debug>>
}