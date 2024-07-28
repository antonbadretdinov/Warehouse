package com.example.warehouse.helpers.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun Long?.toTimeFormatted(): String {
    val timeFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return try {
        timeFormat.format(this)
    } catch (t: Throwable) {
        t.printStackTrace()
        ""
    }
}