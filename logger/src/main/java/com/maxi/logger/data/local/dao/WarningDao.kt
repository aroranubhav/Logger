package com.maxi.logger.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.maxi.logger.data.local.model.Warning
import com.maxi.logger.util.Constants.WARNING_LOGS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
internal interface WarningDao {

    @Insert
    suspend fun insertLog(log: Warning)

    @Query("SELECT * FROM $WARNING_LOGS_TABLE")
    fun getLogs(): Flow<List<Warning>>
}