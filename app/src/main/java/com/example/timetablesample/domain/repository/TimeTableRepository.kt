package com.example.timetablesample.domain.repository

import com.example.timetablesample.data.model.TimeTableDTOModel
import com.example.timetablesample.domain.model.TimeTableResponseModel

interface TimeTableRepository {
    suspend fun getTimeTable() : TimeTableDTOModel
}