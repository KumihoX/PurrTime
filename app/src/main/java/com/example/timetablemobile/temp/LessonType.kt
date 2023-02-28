package com.example.timetablemobile.temp

import androidx.compose.ui.graphics.Color
import com.example.timetablemobile.ui.theme.*

enum class LessonType(val color: Color) {
    Lection(LectionColor),
    Seminar(SeminarColor),
    Practice(PracticeColor),
    Lab(LabColor),
    IndividualLesson(IndividualLessonColor),
    ContactWork(ContactWorkColor),
    ControlWork(ControlWorkColor),
    Consultation(ConsultationColor),
    Booking(BookingColor)
}