package com.codeforsofla.se1t.shipmentFormFeature

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*


data class ShipmentFormState(
    val business: String = "",
    val start: Date = Date(),
    val end: Date = Date()
)

class ShipmentFormViewmodel : ViewModel() {

    val state = mutableStateOf(ShipmentFormState())

    fun onChangeBusiness(business: String) {
        state.value = state.value.copy(
            business = business
        )
    }


    fun onChangeStart(business: String) {
        state.value = state.value.copy(
            business = business
        )
    }




}