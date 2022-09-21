package com.codeforsofla.se1t.shipmentFormFeature

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.math.floor


fun millisFromMidnight(): Long {
    val zone = TimeZone.getTimeZone("EDT")
    val now = Calendar.getInstance(zone).timeInMillis
    val c = Calendar.getInstance(zone).apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return (now - c.timeInMillis)/1000
}

fun Long.toTimeString(): String {
    var totalMinutes = (this / 1000).toInt()
    val minutes = totalMinutes % 60
    var hours = 0
    var timeEnd = "AM"
    while (totalMinutes > 61) {
        hours++
        totalMinutes -= 60
    }

    val minutesString: String = if (minutes > 9) minutes.toString() else "0$minutes"

    if (hours > 11) {
        hours -= 12
        timeEnd = "PM"
    }

    return "$hours:$minutesString $timeEnd"


}

fun Date.getDateFormat(): String = SimpleDateFormat("MM/dd/yyy", Locale.US).format(this)
fun Date.getTimeFormat(): String = SimpleDateFormat("hh:mm aa", Locale.US).format(this)