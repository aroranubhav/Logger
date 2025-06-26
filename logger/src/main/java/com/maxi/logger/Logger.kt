package com.maxi.logger

import android.util.Log

object Logger {

    fun v(tag: String, message: String) {
        Log.v(tag, "${LogLevel.ICONS.VERBOSE.icon} $message")
    }

    fun d(tag: String, message: String) {
        Log.d(tag, "${LogLevel.ICONS.DEBUG.icon} $message")
    }

    fun i(tag: String, message: String) {
        Log.i(tag, "${LogLevel.ICONS.INFO.icon} $message")
    }

    fun w(tag: String, message: String) {
        Log.w(tag, "${LogLevel.ICONS.WARN.icon} $message")
    }

    fun e(tag: String, message: String) {
        Log.e(tag, "${LogLevel.ICONS.ERROR.icon} $message")
    }
}