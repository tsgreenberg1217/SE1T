package com.codeforsofla.se1t.shipmentFormFeature

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*


data class ShipmentFormState(
    val business: String = "",
    val startDay: Date = Date(),
    val endDay: Date = Date(),
    val startTime: Long = 0,
    val endTime: Long = 0,
)

class ShipmentFormViewModel : ViewModel() {

    val state = mutableStateOf(ShipmentFormState())

    fun onChangeBusiness(business: String) {
        state.value = state.value.copy(
            business = business
        )
    }


    fun onChangeStartTime(startTime: Long) {
        state.value = state.value.copy(
            startTime = startTime
        )
    }

    fun onChangeEndTime(endTime: Long) {
        state.value = state.value.copy(
            endTime = endTime
        )
    }

    fun onChangeEndDate(endDate: Date) {
        state.value = state.value.copy(
            endDay = endDate
        )
    }

    fun onChangeStartDate(startDate: Date) {
        state.value = state.value.copy(
            startDay = startDate
        )
    }


}