package com.maxi.logger.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.maxi.logger.util.Constants.ERROR_LOGS_TABLE

@Entity(tableName = ERROR_LOGS_TABLE)
internal data class Error(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("date")
    val date: String
)
