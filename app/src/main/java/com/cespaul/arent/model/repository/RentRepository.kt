package com.cespaul.arent.model.repository

import com.cespaul.arent.model.RentService
import java.util.*

interface RentRepository {

    fun addService(service: RentService)

    fun deleteService(service: RentService)

    fun editService(service: RentService)

    fun getListServices(): ArrayList<RentService>

    fun getItemCount(): Int

    fun getServiceAt(index: Int): RentService
}