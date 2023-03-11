package com.example.timetablemobile.domain.model

data class WeeklySchedule(
    val monday: ArrayList<Lesson> = ArrayList(),
    val tuesday: ArrayList<Lesson> = ArrayList(),
    val wednesday: ArrayList<Lesson> = ArrayList(),
    val thursday: ArrayList<Lesson> = ArrayList(),
    val friday: ArrayList<Lesson> = ArrayList(),
    val saturday: ArrayList<Lesson> = ArrayList(),
    val sunday: ArrayList<Lesson> = ArrayList()
)

fun WeeklySchedule.sortTimeDescending(): WeeklySchedule {


    return WeeklySchedule(

    )
}