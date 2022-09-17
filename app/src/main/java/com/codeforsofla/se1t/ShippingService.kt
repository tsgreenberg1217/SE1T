package com.codeforsofla.se1t

data class ShippingData(
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
            ShippingData("Todd", "Amazon", "11:00AM - 12:00PM"),
            ShippingData("Brad", "Mikes Fishes", "12:00PM - 1:00PM"),
            ShippingData("Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData("Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData("Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData("Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData("Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData("Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData("Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData("Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData("Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData("Matthew", "Publix Foods", "2:00PM - 3:00PM"),
        )
    }

}