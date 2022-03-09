package com.example.timetablesample.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TimeTableDTOModel(

	@field:SerializedName("timetable")
	val timetable: Timetable
) : Parcelable

@Parcelize
data class Timetable(

	@field:SerializedName("departures")
	val departures: List<DeparturesItem>
) : Parcelable

@Parcelize
data class Datetime(

	@field:SerializedName("tz")
	val tz: String,

	@field:SerializedName("timestamp")
	val timestamp: Int
) : Parcelable

@Parcelize
data class DeparturesItem(

	@field:SerializedName("datetime")
	val datetime: Datetime,

	@field:SerializedName("line_code")
	val lineCode: String,

	@field:SerializedName("direction")
	val direction: String
) : Parcelable
