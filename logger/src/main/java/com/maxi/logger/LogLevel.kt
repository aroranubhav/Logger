package com.maxi.logger

internal object LogLevel {

    enum class ICONS(val icon: String) {
        VERBOSE("🔍"),
        DEBUG("🐞"),
        INFO("ℹ️"),
        WARN("⚠️"),
        ERROR("❌")
    }
}