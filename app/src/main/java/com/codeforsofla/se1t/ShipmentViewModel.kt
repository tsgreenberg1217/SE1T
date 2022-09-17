package com.codeforsofla.se1t

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

enum class BottomNavState(){
    Personal,
    All
}


data class ShipmentState(
    val shipmentList: List<ShippingData> = listOf(),
    val bottomNavState: BottomNavState = BottomNavState.All
)

class ShipmentViewModel : ViewModel() {

    val state: MutableState<ShipmentState> = mutableStateOf(ShipmentState())


    init {
        getFullList()
    }

    fun getFullList() {

        state.value = state.value.copy(
            shipmentList = MockShippingService().getShipments(),
            bottomNavState = BottomNavState.All
        )
    }

    fun filterList(recipient: String) {
        state.value = state.value.copy(
            shipmentList = MockShippingService().getShipments().filter { it.recipient == recipient },
            bottomNavState = BottomNavState.Personal
        )
    }
}