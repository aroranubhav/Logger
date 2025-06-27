package com.maxi.logger.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {

    fun getCurrentDataTime(): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formattedDateTime = currentDateTime.format(formatter)
        return formattedDateTime
    }

    fun getCurrentDate(): String =
        "${LocalDate.now()}"

}