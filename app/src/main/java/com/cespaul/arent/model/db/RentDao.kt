package com.cespaul.arent.model.db

import com.cespaul.arent.model.RentService

interface RentDao {

    //@Insert
    fun insertServices(services: List<RentService>)

    //@Query("select * from services")
    fun selectServices()

    //@Query()
    fun deleteService()

}