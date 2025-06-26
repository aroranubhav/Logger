package com.maxi.logger

import android.content.Context
import android.util.Log
import com.maxi.logger.data.local.LoggerDatabase
import com.maxi.logger.data.local.model.Debug
import com.maxi.logger.data.local.model.Error
import com.maxi.logger.data.local.model.Info
import com.maxi.logger.data.local.model.Verbose
import com.maxi.logger.data.local.model.Warning
import com.maxi.logger.util.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Logger private constructor(
    context: Context
) {

    private var loggerDb: LoggerDatabase

    init {
        val appContext = context.applicationContext
        loggerDb = LoggerDatabase.getInstance(appContext)
    }

    companion object {
        @Volatile
        private var INSTANCE: Logger? = null

        fun getInstance(context: Context): Logger {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Logger(context.applicationContext).also {
                    INSTANCE = it
                }
            }
        }
    }

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    fun v(tag: String, message: String) {
        val logMessage = "${LogLevel.ICONS.VERBOSE.icon} $message"
        scope.launch {
            loggerDb.verboseDao().insertLog(Verbose(message = logMessage))
        }
        Log.v(tag, logMessage)
    }

    fun d(tag: String, message: String) {
        val logMessage = "${LogLevel.ICONS.DEBUG.icon} $message"
        scope.launch {
            loggerDb.debugDao().insertLog(Debug(message = logMessage))
        }
        Log.d(tag, logMessage)
    }

    fun i(tag: String, message: String) {
        val logMessage = "${LogLevel.ICONS.INFO.icon} $message"
        scope.launch {
            loggerDb.infoDao().insertLog(Info(message = logMessage))
        }
        Log.i(tag, logMessage)
    }

    fun w(tag: String, message: String) {
        val logMessage = "${LogLevel.ICONS.WARN.icon} $message"
        scope.launch {
            loggerDb.warningDao().insertLog(Warning(message = logMessage))
        }
        Log.w(tag, logMessage)
    }

    fun e(tag: String, message: String) {
        val logMessage = "${LogLevel.ICONS.ERROR.icon} $message"
        scope.launch {
            loggerDb.errorDao().insertLog(Error(message = logMessage))
        }
        Log.e(tag, logMessage)
    }

    fun close() {
        loggerDb.close()
        job.cancel()
    }
}