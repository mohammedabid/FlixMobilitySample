package com.example.timetablesample.data.repository

import com.example.timetablesample.data.model.TimeTableDTOModel
import com.example.timetablesample.data.remote.TimeTableApi
import com.example.timetablesample.domain.model.TimeTableResponseModel
import com.example.timetablesample.domain.repository.TimeTableRepository
import javax.inject.Inject

class TimeTableRepositoryImpl @Inject constructor(private val timeTableApi: TimeTableApi) : TimeTableRepository {
    override suspend fun getTimeTable(): TimeTableDTOModel {
        return timeTableApi.getTimeTable()
    }
}