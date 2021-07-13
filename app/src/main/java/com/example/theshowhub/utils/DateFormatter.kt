package com.example.theshowhub.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Locale

class DateFormatter {

    fun format(originalDate: String, inputFormat: String, outputFormat: String): String = try {
        val format = SimpleDateFormat(inputFormat, Locale.getDefault())
        val date = format.parse(originalDate)
        val newFormat = SimpleDateFormat(outputFormat, Locale.getDefault())

        date?.run { newFormat.format(date) } ?: ""
    } catch (exception: Exception) {
        Log.e(this::class.java.simpleName, exception.message, exception)
        ""
    }

    companion object {
        const val YYYY_MM_DD = "yyyy-MM-dd"
        const val MMM_YYYY = "MMM yyyy"
    }

}