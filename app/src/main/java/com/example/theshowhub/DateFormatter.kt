package com.example.theshowhub

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Locale

class DateFormatter {

    fun format(originalDate: String, inputFormat: String, outputFormat: String): String = try {
        val format = SimpleDateFormat(inputFormat, Locale.US)
        val date = format.parse(originalDate)
        val newFormat = SimpleDateFormat(outputFormat, Locale.US)

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