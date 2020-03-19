package com.cespaul.arent.model

data class RentService(
    var id: Int,
    val nameService: String,
    val rateService: Int,
    val amtService: Int,
    val sumService: Int
)