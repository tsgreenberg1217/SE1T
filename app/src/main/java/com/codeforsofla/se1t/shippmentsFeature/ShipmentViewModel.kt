package com.codeforsofla.se1t

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.codeforsofla.se1t.shippmentsFeature.MockShippingService
import com.codeforsofla.se1t.shippmentsFeature.ShippingData

enum class BottomNavState() {
    Personal,
    All
}


data class ShipmentState(
    val shipmentsToFilter: List<ShippingData> = listOf(),
    val filteredShipments: List<ShippingData> = listOf(),
    val personalShipments: List<ShippingData> = listOf(),
    val bottomNavState: BottomNavState = BottomNavState.All
)

class ShipmentViewModel : ViewModel() {

    private var initialData: List<ShippingData> =
        MockShippingService().getShipments().toMutableList()
    val state: MutableState<ShipmentState> = mutableStateOf(ShipmentState())


    init {
        state.value = state.value.copy(
            filteredShipments = initialData.toMutableList(),
            personalShipments = initialData.filter { it.recipient == "Todd" },
            bottomNavState = BottomNavState.All
        )


    }

    fun filterList(recipient: String) {
        state.value = state.value.copy(
            filteredShipments = MockShippingService().getShipments()
                .filter { it.recipient == recipient },
            bottomNavState = BottomNavState.Personal
        )
    }

    fun filterById(data: ShippingData, isAdd: Boolean) {
        val ids = state.value.shipmentsToFilter.toMutableList().apply {
            if (isAdd) add(data) else remove(data)
        }

        val filteredShipments = initialData.filter { d -> ids.any { it.id == d.id } }

        state.value = state.value.copy(
            shipmentsToFilter = ids,
            filteredShipments = filteredShipments
        )
    }
}