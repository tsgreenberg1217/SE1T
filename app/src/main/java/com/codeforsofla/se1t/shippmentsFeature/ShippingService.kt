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
            ShippingData(2,"Brad", "Lancer Deets Testing", "12:30PM - 1:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "2:00PM - 3:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "4:00PM - 5:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "6:00PM - 7:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "8:00PM - 9:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "10:00PM - 11:00PM"),
            ShippingData(3,"Matthew", "Publix Foods", "1:00AM - 2:00AM"),
            ShippingData(3,"Matthew", "Publix Foods", "3:00AM - 4:00AM"),
            ShippingData(3,"Matthew", "Publix Foods", "5:00AM - 6:00AM"),
            ShippingData(3,"Matthew", "Publix Foods", "7:00AM - 8:00AM"),
            ShippingData(3,"Matthew", "Publix Foods", "9:00AM - 10:00AM"),
        )
    }

}