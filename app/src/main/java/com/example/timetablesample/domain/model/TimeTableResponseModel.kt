package com.example.timetablesample.domain.model

import android.os.Parcelable
import com.example.timetablesample.data.model.Datetime
import com.example.timetablesample.data.model.DeparturesItem
import com.example.timetablesample.data.model.Timetable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimeTableResponseModel(
    val timetables: List<TimeTableModel>
) : Parcelable

@Parcelize
data class TimeTableModel(

    @field:SerializedName("tz")
    val tz: String,

    @field:SerializedName("timestamp")
    val timestamp: Int,

    @field:SerializedName("line_code")
    val lineCode: String,

    @field:SerializedName("direction")
    val direction: String
) : Parcelable