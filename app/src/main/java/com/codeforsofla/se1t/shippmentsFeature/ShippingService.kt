package com.codeforsofla.se1t.shippmentsFeature

data class ShippingData(
    val id:Long = 0,
    val recipient: String,
    val business: String,
    val timeRange:String
)


interface ShippingService {
    fun getShipments(): List<ShippingData>
}

class MockShippingService : ShippingService {
    override fun getShipments(): List<ShippingData> {
        return listOf(
            ShippingData(1,"Todd", "Amazon", "11:00AM - 12:00PM"),
            ShippingData(2,"Brad", "Mikes Fishes", "12:00PM - 1:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "2:00PM - 3:00PM"),
        )
    }

}