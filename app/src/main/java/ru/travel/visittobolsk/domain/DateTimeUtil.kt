package ru.travel.visittobolsk.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.toLocalDateTime

class DateTimeUtil {
    companion object {
        val currentHour = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time.hour
        val currentMin = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time.minute
        val currentDayOfWeek = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).dayOfWeek.isoDayNumber
        fun getDayName(index: Int): String {
            return when (index) {
                0 -> "Понедельник"
                1 -> "Вторник"
                2 -> "Среда"
                3 -> "Четверг"
                4 -> "Пятница"
                5 -> "Суббота"
                6 -> "Воскресенье"
                else -> ""
            }
        }
    }
}