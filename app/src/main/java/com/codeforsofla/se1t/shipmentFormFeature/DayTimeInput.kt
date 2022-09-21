package com.codeforsofla.se1t.shipmentFormFeature

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun DateTimeInput(
    ctx: Context,
    title: String,
    dayValue: Date,
    timeValue: Long,
    onDateChange: (Date) -> Unit,
    onTimeChange: (Long) -> Unit,
    cornerRadius: Dp = 3.dp
) {
    val dayPicker = remember {
        getDayPicker(ctx, onDateChange)
    }
    val timePicker = remember {
        getTimePicker(ctx, onTimeChange)
    }
    Column {
        Text(text = title)
        Row(Modifier.wrapContentWidth()) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .clickable { dayPicker.show() },
                value = dayValue.getDateFormat(),
                label = {
                    Text(text = "Date")
                },
                onValueChange = {
                },
                enabled = false,
                shape = RoundedCornerShape(topStart = cornerRadius, bottomStart = cornerRadius)
            )

            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .clickable { timePicker.show() },
                value = if (timeValue == 0L) Date().getTimeFormat() else timeValue.toTimeString(),
                label = {
                    Text(text = "Time")
                },
                onValueChange = {
                },
                enabled = false,
                shape = RoundedCornerShape(topEnd = cornerRadius, bottomEnd = cornerRadius)
            )

        }
    }
}

internal fun getDayPicker(context: Context, onDayChange: (Date) -> Unit): DatePickerDialog {
    return DatePickerDialog(context).apply {
        setOnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            val newDate: Date =
                Calendar.getInstance().apply { set(year, monthOfYear, dayOfMonth) }.time
            onDayChange(newDate)
        }
    }
}

internal fun getTimePicker(context: Context, onTimeChange: (Long) -> Unit): TimePickerDialog {
    return TimePickerDialog(
        context,
        { _, h, m ->
            val minutes = h * 60 + m
            onTimeChange(minutes * 1000.toLong())
        },
        12,
        0,
        false
    )
}