package com.example.timetablesample.domain.usecase

import com.example.timetablesample.common.Resource
import com.example.timetablesample.data.model.Datetime
import com.example.timetablesample.data.model.DeparturesItem
import com.example.timetablesample.data.model.Timetable
import com.example.timetablesample.domain.model.TimeTableModel
import com.example.timetablesample.domain.model.TimeTableResponseModel
import com.example.timetablesample.domain.repository.TimeTableRepository
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class TimeTableUseCase @Inject constructor(private val repository: TimeTableRepository) {

    operator fun invoke() : Flow<Resource<TimeTableResponseModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getTimeTable()
            val domainData =
                if (data.timetable.departures != null)
                    data.timetable.departures.map { it -> timeTableModelMapper(it)} else emptyList()
            val response = timeTableResponseModelMapper(domainData)
            emit(Resource.Success(data = response))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }

    private fun timeTableModelMapper(departuresItem: DeparturesItem) : TimeTableModel {
        return TimeTableModel(
            tz = departuresItem.datetime.tz ,
            timestamp = departuresItem.datetime.timestamp,
            lineCode = departuresItem.lineCode,
            direction = departuresItem.direction
        )
    }

    private fun timeTableResponseModelMapper(list: List<TimeTableModel>) : TimeTableResponseModel {
        return TimeTableResponseModel(
            timetables = list
        )
    }
}