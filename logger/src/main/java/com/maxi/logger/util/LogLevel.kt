package com.maxi.logger.util

internal object LogLevel {

    enum class ICONS(val icon: String) {
        VERBOSE("🔍"),
        DEBUG("🐞"),
        INFO("ℹ️"),
        WARN("⚠️"),
        ERROR("❌")
    }
}