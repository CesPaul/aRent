package com.cespaul.arent.model.repository

import com.cespaul.arent.model.RentService

interface RentRepository {

    fun getServicesFromDb(): RentService

}