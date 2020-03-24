package com.cespaul.arent.model.repository

import com.cespaul.arent.model.RentService
import java.util.*

class RentRepositoryImpl() : RentRepository {

    companion object {
        var listServices = ArrayList<RentService>()
    }

    private var idService = 0

    override fun addService(service: RentService) {
        listServices.add(service)
        service.id = idService
        idService++
    }

    override fun deleteService(service: RentService) {
        for (listService in listServices) {
            if (listService.id == service.id) {
                listServices.remove(listService)
                return
            }
        }
    }

    override fun getListServices(): ArrayList<RentService> {
        return listServices
    }

    override fun getItemCount(): Int = listServices.size

    override fun getServiceAt(index: Int): RentService = listServices[index]

}