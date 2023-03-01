package com.example.timetablemobile.ui.presentation.mainscreen

import java.text.SimpleDateFormat
import java.util.*

fun getFutureDates(
    count: Int,
    startCalendar: Calendar = Calendar.getInstance(Locale.getDefault()),
    includeStart: Boolean = true
): MutableList<Date> {
    val futureDateList = mutableListOf<Date>()
    if (includeStart)
        futureDateList.add(startCalendar.time)
    for (i in 0 until count) {
        startCalendar.add(Calendar.DATE, 1)
        futureDateList.add(startCalendar.time)
    }
    return futureDateList
}

fun getDayNumber(date: Date): String =
    SimpleDateFormat("d", Locale.getDefault()).format(date)

fun getMonthName(date: Date): String {
    val calendar = Calendar.getInstance()
    calendar.time = date
    val monthNumber = calendar.get(Calendar.MONTH)
    val monthsList = listOf(
        "Январь",
        "Февраль",
        "Март",
        "Апрель",
        "Май",
        "Июнь",
        "Июль",
        "Август",
        "Сентябрь",
        "Октябрь",
        "Ноябрь",
        "Декабрь"
    )
    return monthsList[monthNumber]
}

fun getYear4Letters(date: Date): String =
    SimpleDateFormat("YYYY", Locale("ru", "RU")).format(date)

fun getDay3LettersName(date: Date): String = SimpleDateFormat("EE", Locale("ru", "RU")).format(date)