package com.maxi.logger.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maxi.logger.data.local.dao.DebugDao
import com.maxi.logger.data.local.dao.ErrorDao
import com.maxi.logger.data.local.dao.InfoDao
import com.maxi.logger.data.local.dao.VerboseDao
import com.maxi.logger.data.local.dao.WarningDao
import com.maxi.logger.data.local.model.Debug
import com.maxi.logger.data.local.model.Error
import com.maxi.logger.data.local.model.Info
import com.maxi.logger.data.local.model.Verbose
import com.maxi.logger.data.local.model.Warning
import com.maxi.logger.util.Constants.LOGGER_DATABASE

@Database(
    entities = [Verbose::class, Debug::class,
        Info::class, Warning::class, Error::class],
    version = 1
)
internal abstract class LoggerDatabase : RoomDatabase() {

    abstract fun verboseDao(): VerboseDao

    abstract fun debugDao(): DebugDao

    abstract fun infoDao(): InfoDao

    abstract fun warningDao(): WarningDao

    abstract fun errorDao(): ErrorDao

    companion object {
        @Volatile
        private var INSTANCE: LoggerDatabase? = null

        fun getInstance(context: Context): LoggerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context,
                        LoggerDatabase::class.java,
                        LOGGER_DATABASE
                    ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}