package com.example.timetablemobile.domain.model

import androidx.compose.ui.graphics.Color
import com.example.timetablemobile.R
import com.example.timetablemobile.ui.theme.*

enum class LessonType(
    val typeNameId: Int,
    val color: Color
) {
    Lection(R.string.lection, LectionColor),
    Seminar(R.string.seminar, SeminarColor),
    Practice(R.string.practice, PracticeColor),
    Lab(R.string.lab, LabColor),
    IndividualLesson(R.string.individual_lesson, IndividualLessonColor),
    ContactWork(R.string.contact_work, ContactWorkColor),
    ControlWork(R.string.control_work, ControlWorkColor),
    Consultation(R.string.consultation, ConsultationColor),
    Booking(R.string.booking, BookingColor)
}