package com.example.timetablesample.data.remote

import com.example.timetablesample.data.model.TimeTableDTOModel
import retrofit2.http.GET

interface TimeTableApi {
    @GET("mobile/v1/network/station/1/timetable.json")
    suspend fun getTimeTable() : TimeTableDTOModel
}