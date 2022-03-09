package com.example.timetablesample.ui.list

import com.example.timetablesample.domain.model.TimeTableModel

data class TimeTableUiState(
    val isLoading: Boolean = false,
    val data: List<TimeTableModel>? = null,
    val error: String = ""
)