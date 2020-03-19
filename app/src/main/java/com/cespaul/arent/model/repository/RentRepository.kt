package com.cespaul.arent.model.repository

import com.cespaul.arent.model.RentService

interface RentRepository {

    fun addService(service: RentService)

    fun deleteService(service: RentService)

    //fun getServicesFromDb(): RentService

}