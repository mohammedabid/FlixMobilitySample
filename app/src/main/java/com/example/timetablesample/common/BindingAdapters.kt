package com.example.timetablesample.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter(value = ["millis","tz"], requireAll = true)
fun millisToDate(view: TextView, millis: Long, tz: String) {

//    val c = Calendar.getInstance(TimeZone.getTimeZone(tz))
    val c = Calendar.getInstance()
    c.timeInMillis = millis*1000
    val newDate = c.time

    val sdf = SimpleDateFormat("HH:mm", Locale.US)
    sdf.timeZone = TimeZone.getTimeZone(tz)

    view.text = sdf.format(newDate)
}
