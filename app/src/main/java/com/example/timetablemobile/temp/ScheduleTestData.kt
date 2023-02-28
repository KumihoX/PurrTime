package com.example.timetablemobile.temp

// это тестовые данные для того чтобы проверять вывод оных на главном экране
// не передается параметр - выводится одно расписание
// что-то передается - выводится другое расписание
// вот и всо
fun GetScheduleTestData(param: Int = 1): ScheduleTestDto {
    val cabinet1 = CabinetTestDto(
        "Computer class",
        127
    )
    val cabinet2 = CabinetTestDto(
        "Not computer class",
        227
    )

    val timeslot1 = TimeslotTestDto(
        "10:20",
        "8:45"
    )
    val timeslot2 = TimeslotTestDto(
        "12:10",
        "10:35"
    )
    val timeslot3 = TimeslotTestDto(
        "14:00",
        "12:25"
    )
    val timeslot4 = TimeslotTestDto(
        "16:20",
        "14:45"
    )
    val timeslot5 = TimeslotTestDto(
        "18:10",
        "16:35"
    )

    val testLesson1 = LessonTestDto(
        LessonType.Lection,
        cabinet1,
        "2.03.2023",
        listOf(972103, 972102, 972103),
        "Математический анализ",
        "Даммер Диана Дамировна",
        timeslot1
    )
    val testLesson2 = LessonTestDto(
        LessonType.Consultation,
        cabinet1,
        "2.03.2023",
        listOf(972103),
        "Математический анализ",
        "Шкленник Мария Александровна",
        timeslot2
    )
    val testLesson3 = LessonTestDto(
        LessonType.Lab,
        cabinet2,
        "2.03.2023",
        listOf(972103),
        "Математический анализ",
        "Шкленник Мария Александровна",
        timeslot3
    )

    val testLesson4 = LessonTestDto(
        LessonType.Practice,
        cabinet2,
        "2.04.2023",
        listOf(972103),
        "Основы командной разработки и прочего",
        "Змеев Денис Олегович",
        timeslot4
    )
    val testLesson5 = LessonTestDto(
        LessonType.Seminar,
        cabinet1,
        "2.02.2023",
        listOf(972103, 972102),
        "Основы командной разработки и прочего",
        "Змеев Денис Олегович",
        timeslot5
    )


    return if (param == 1)
        ScheduleTestDto(
            schedule = listOf(
                testLesson1,
                testLesson2,
                testLesson3
            )
        )
    else
        ScheduleTestDto(
            schedule = listOf(
                testLesson4,
                testLesson5
            )
        )
}
