package com.maxi.logger.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.maxi.logger.util.Constants.INFO_LOGS_TABLE

@Entity(tableName = INFO_LOGS_TABLE)
internal data class Info(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("date")
    val date: String
)
