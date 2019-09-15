package de.richargh.buggetfx.time

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.ZonedDateTime

class Cronus {

    companion object {
        fun of(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int) = Moment(
                ZonedDateTime.of(LocalDate.of(year, month, day), LocalTime.of(hour, minute, second), ZoneOffset.UTC))
    }

}
