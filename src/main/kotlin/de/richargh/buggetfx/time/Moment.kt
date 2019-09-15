package de.richargh.buggetfx.time

import java.time.*
import java.time.format.DateTimeFormatter

/**
 * Moments use UTC
 */
data class Moment private constructor(private val rawValueUtcInMillis: Long): Comparable<Moment> {

    constructor(zonedDateTime: ZonedDateTime): this(
            zonedDateTime.withZoneSameInstant(ZoneOffset.UTC).toInstant().toEpochMilli())

    override fun compareTo(other: Moment) = rawValueUtcInMillis.compareTo(other.rawValueUtcInMillis)

    override fun toString(): String = formatWithPattern()

    private fun formatWithPattern(pattern: String = "yyyy-MM-dd HH:mm z") =
            toUtcZonedDateTime().format(DateTimeFormatter.ofPattern(pattern))

    private fun toUtcZonedDateTime(): ZonedDateTime =
            ZonedDateTime.ofInstant(Instant.ofEpochMilli(rawValueUtcInMillis), ZoneOffset.UTC)
}
