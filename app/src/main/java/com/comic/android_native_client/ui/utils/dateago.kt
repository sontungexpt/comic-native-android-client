package com.comic.android_native_client.ui.utils

import android.content.Context
import com.comic.android_native_client.R
import kotlinx.datetime.toJavaInstant
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.math.floor

const val SECONDS_IN_A_MINUTE = 60
const val SECONDS_IN_AN_HOUR = 3600
const val SECONDS_IN_A_DAY = 86400
const val SECONDS_IN_A_MONTH = 2592000 // Giả sử 30 ngày mỗi tháng
const val SECONDS_IN_A_YEAR = 31536000 // Giả sử 365 ngày mỗi năm

const val DEFAULT_THRESHOLD_SECONDS = 20 * SECONDS_IN_A_DAY

fun formatSecondsAgo(context: Context, seconds: Int): String {
    return context.getString(R.string.time_seconds_ago, seconds)
}

fun formatMinutesAgo(context: Context, minutes: Int): String {
    return context.getString(R.string.time_minutes_ago, minutes)
}

fun formatHoursAgo(context: Context, hours: Int): String {
    return context.getString(R.string.time_hours_ago, hours)
}

fun formatDaysAgo(context: Context, days: Int): String {
    return context.getString(R.string.time_days_ago, days)
}

fun formatMonthsAgo(context: Context, months: Int): String {
    return context.getString(R.string.time_months_ago, months)
}

fun formatYearsAgo(context: Context, years: Int): String {
    return context.getString(R.string.time_years_ago, years)
}

fun formatTimeAgo(
    context: Context,
    dateAgo: String,
    thresholdSeconds: Int = DEFAULT_THRESHOLD_SECONDS
): String {
    if (dateAgo.isBlank()) {
        return context.getString(R.string.time_unknown)
    }

    val formatter = DateTimeFormatter.ISO_DATE_TIME
    val dateAgo = try {
        LocalDateTime.parse(dateAgo, formatter)
    } catch (e: Exception) {
        throw IllegalArgumentException("Invalid date format")
    }

    val nowSeconds = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
    val updatedSeconds = dateAgo.toEpochSecond(ZoneOffset.UTC)

    val diffSeconds = (nowSeconds - updatedSeconds).toInt()
    return when {
        diffSeconds >= thresholdSeconds -> {
            val dateFormatter =
                DateTimeFormatter.ofPattern(context.getString(R.string.time_date_format))
            dateAgo.format(dateFormatter)
        }

        diffSeconds < SECONDS_IN_A_MINUTE -> formatSecondsAgo(context, diffSeconds)
        diffSeconds < SECONDS_IN_AN_HOUR -> formatMinutesAgo(
            context,
            floor(diffSeconds / SECONDS_IN_A_MINUTE.toDouble()).toInt()
        )

        diffSeconds < SECONDS_IN_A_DAY -> formatHoursAgo(
            context,
            floor(diffSeconds / SECONDS_IN_AN_HOUR.toDouble()).toInt()
        )

        diffSeconds < SECONDS_IN_A_MONTH -> formatDaysAgo(
            context,
            floor(diffSeconds / SECONDS_IN_A_DAY.toDouble()).toInt()
        )

        diffSeconds < SECONDS_IN_A_YEAR -> formatMonthsAgo(
            context,
            floor(diffSeconds / SECONDS_IN_A_MONTH.toDouble()).toInt()
        )

        else -> formatYearsAgo(context, floor(diffSeconds / SECONDS_IN_A_YEAR.toDouble()).toInt())
    }
}

fun formatTimeAgo(
    context: Context,
    dateAgo: Instant,
    thresholdSeconds: Int = DEFAULT_THRESHOLD_SECONDS
): String {
    val now = Instant.now()
    val diffSeconds = (now.epochSecond - dateAgo.epochSecond).toInt()

    return when {
        diffSeconds >= thresholdSeconds -> {
            // Format ngày tháng
            val dateFormatter =
                DateTimeFormatter.ofPattern(context.getString(R.string.time_date_format))
                    .withZone(ZoneId.systemDefault())
            dateFormatter.format(dateAgo)
        }

        diffSeconds < SECONDS_IN_A_MINUTE -> formatSecondsAgo(context, diffSeconds)
        diffSeconds < SECONDS_IN_AN_HOUR -> formatMinutesAgo(
            context,
            floor(diffSeconds / SECONDS_IN_A_MINUTE.toDouble()).toInt()
        )

        diffSeconds < SECONDS_IN_A_DAY -> formatHoursAgo(
            context,
            floor(diffSeconds / SECONDS_IN_AN_HOUR.toDouble()).toInt()
        )

        diffSeconds < SECONDS_IN_A_MONTH -> formatDaysAgo(
            context,
            floor(diffSeconds / SECONDS_IN_A_DAY.toDouble()).toInt()
        )

        diffSeconds < SECONDS_IN_A_YEAR -> formatMonthsAgo(
            context,
            floor(diffSeconds / SECONDS_IN_A_MONTH.toDouble()).toInt()
        )

        else -> formatYearsAgo(context, floor(diffSeconds / SECONDS_IN_A_YEAR.toDouble()).toInt())
    }
}

fun formatTimeAgo(
    context: Context,
    dateAgo: kotlinx.datetime.Instant,
    thresholdSeconds: Int = DEFAULT_THRESHOLD_SECONDS
): String {
    return formatTimeAgo(context, dateAgo.toJavaInstant(), thresholdSeconds)
}
