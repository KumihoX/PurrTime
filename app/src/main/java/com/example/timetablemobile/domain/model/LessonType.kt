package com.example.timetablemobile.domain.model

import androidx.compose.ui.graphics.Color

enum class LessonType(
    val typeName: String,
    val color: Color
) {
    0(LectionColor),
    Seminar(SeminarColor),
    Practice(PracticeColor),
    Lab(LabColor),
    IndividualLesson(IndividualLessonColor),
    ContactWork(ContactWorkColor),
    ControlWork(ControlWorkColor),
    Consultation(ConsultationColor),
    Booking(BookingColor)
}