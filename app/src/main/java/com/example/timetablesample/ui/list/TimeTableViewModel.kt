package com.example.timetablesample.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timetablesample.common.Resource
import com.example.timetablesample.domain.usecase.TimeTableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TimeTableViewModel @Inject constructor(private val timeTableUseCase: TimeTableUseCase) :
    ViewModel() {


    private val _timeTableList = MutableStateFlow<TimeTableUiState>(TimeTableUiState())
    val timeTableList: StateFlow<TimeTableUiState> = _timeTableList


    fun getTimeTable() {
        timeTableUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _timeTableList.value = TimeTableUiState(isLoading = true)
                }
                is Resource.Success -> {
                    _timeTableList.value = TimeTableUiState(data = it.data.timetables)
                }
                is Resource.Error -> {
                    _timeTableList.value = TimeTableUiState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}