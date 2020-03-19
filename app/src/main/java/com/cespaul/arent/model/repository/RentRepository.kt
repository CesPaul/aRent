package com.cespaul.arent.model.repository

import com.cespaul.arent.model.RentService

interface RentRepository {

    fun addService(service: RentService)

    fun deleteService(service: RentService)

    fun editService(service: RentService)

    fun getItemCount(): Int

    fun getServiceAt(index: Int): RentService

    //fun getServicesFromDb(): RentService

}