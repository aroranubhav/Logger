package com.maxi.logger

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.maxi.logger.data.local.LoggerDatabase
import com.maxi.logger.data.local.model.Debug
import com.maxi.logger.data.local.model.Error
import com.maxi.logger.data.local.model.Info
import com.maxi.logger.data.local.model.Verbose
import com.maxi.logger.data.local.model.Warning
import com.maxi.logger.util.Constants.DEBUG_LOGS_FILE_NAME_PREFIX
import com.maxi.logger.util.Constants.ERROR_LOGS_FILE_NAME_PREFIX
import com.maxi.logger.util.Constants.INFO_LOGS_FILE_NAME_PREFIX
import com.maxi.logger.util.Constants.VERBOSE_LOGS_FILE_NAME_PREFIX
import com.maxi.logger.util.Constants.WARNING_LOGS_FILE_NAME_PREFIX
import com.maxi.logger.util.DefaultDispatcherProvider
import com.maxi.logger.util.DispatcherProvider
import com.maxi.logger.util.LogLevel
import com.maxi.logger.util.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File

class Logger private constructor(
    context: Context,
    private val maxFileSize: Long,
    private val writeToFile: Boolean
) {

    private var appContext: Context
    private var loggerDb: LoggerDatabase
    private var dispatcherProvider: DispatcherProvider
    private val job = Job()
    private val scope: CoroutineScope

    init {
        appContext = context.applicationContext
        loggerDb = LoggerDatabase.getInstance(appContext)
        dispatcherProvider = DefaultDispatcherProvider()
        scope = CoroutineScope(dispatcherProvider.io + job)
    }

    companion object {
        @Volatile
        private var INSTANCE: Logger? = null

        fun getInstance(
            context: Context,
            maxFileSize: Long,
            writeToFile: Boolean = false
        ): Logger {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Logger(
                    context.applicationContext,
                    maxFileSize,
                    writeToFile
                ).also {
                    INSTANCE = it
                }
            }
        }
    }

    fun v(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.VERBOSE.icon} $message"
        scope.launch {
            if (writeToFile) {
                val dao = loggerDb.verboseDao()
                dao.insertLog(
                    Verbose(
                        message = logMessage,
                        date = Utils.getCurrentDataTime()
                    )
                )
                val logs = dao.getLogs().first()
                val fileName = VERBOSE_LOGS_FILE_NAME_PREFIX + Utils.getCurrentDate() + ".txt"
                writeToFile(logs, fileName)
            }
        }
        Log.v(tag, logMessage)
    }

    fun d(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.DEBUG.icon} $message"
        scope.launch {
            if (writeToFile) {
                val dao = loggerDb.debugDao()
                dao.insertLog(
                    Debug(
                        message = logMessage,
                        date = Utils.getCurrentDataTime()
                    )
                )
                val logs = dao.getLogs().first()
                val fileName = DEBUG_LOGS_FILE_NAME_PREFIX + Utils.getCurrentDate() + ".txt"
                writeToFile(logs, fileName)
            }
        }
        Log.d(tag, logMessage)
    }

    fun i(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.INFO.icon} $message"
        scope.launch {
            if (writeToFile) {
                val dao = loggerDb.infoDao()
                dao.insertLog(
                    Info(
                        message = logMessage,
                        date = Utils.getCurrentDataTime()
                    )
                )
                val logs = dao.getLogs().first()
                val fileName = INFO_LOGS_FILE_NAME_PREFIX + Utils.getCurrentDate() + ".txt"
                writeToFile(logs, fileName)
            }
        }
        Log.i(tag, logMessage)
    }

    fun w(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.WARN.icon} $message"
        scope.launch {
            if (writeToFile) {
                val dao = loggerDb.warningDao()
                dao.insertLog(
                    Warning(
                        message = logMessage,
                        date = Utils.getCurrentDataTime()
                    )
                )
                val logs = dao.getLogs().first()
                val fileName = WARNING_LOGS_FILE_NAME_PREFIX + Utils.getCurrentDate() + ".txt"
                writeToFile(logs, fileName)
            }
        }
        Log.w(tag, logMessage)
    }

    fun e(tag: String, message: String) {
        val logMessage = "${LogLevel.Icon.ERROR.icon} $message"
        scope.launch {
            if (writeToFile) {
                val dao = loggerDb.errorDao()
                dao.insertLog(
                    Error(
                        message = logMessage,
                        date = Utils.getCurrentDataTime()
                    )
                )
                val logs = dao.getLogs().first()
                val fileName = ERROR_LOGS_FILE_NAME_PREFIX + Utils.getCurrentDate() + ".txt"
                writeToFile(logs, fileName)
            }
        }
        Log.e(tag, logMessage)
    }

    private fun <T> writeToFile(logs: List<T>, fileName: String) {
        val gson = Gson()
        val logsString = gson.toJson(logs)

        scope.launch {
            val file = File(
                appContext.filesDir,
                fileName
            )

            if (file.exists()) {
                file.writeText("") //clear file contents
            }
            file.appendText("$logsString \n")
        }
    }

    fun close() {
        loggerDb.close()
        job.cancel()
    }
}