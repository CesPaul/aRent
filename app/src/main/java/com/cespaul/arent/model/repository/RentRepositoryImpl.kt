package com.cespaul.arent.model.repository

import com.cespaul.arent.model.RentService
import com.cespaul.arent.model.db.RentDatabase
import java.util.*

class RentRepositoryImpl(private val rentDb: RentDatabase) : RentRepository {

    private var listServices = ArrayList<RentService>()

    init {
        listServices.addAll(rentDb.rentDao().selectServices())
    }

    private var idService = 0

    override fun addService(service: RentService) {
        rentDb.rentDao().insertService(service)
        listServices.add(service)
        /*service.id = idService
        idService++*/
    }

    override fun deleteService(service: RentService) {
        rentDb.rentDao().deleteService(service.id)
        for (listService in listServices) {
            if (listService.id == service.id) {
                listServices.remove(listService)
                return
            }
        }
    }

    override fun editService(service: RentService) {
        rentDb.rentDao().editService(service)
    }

    override fun getListServices(): ArrayList<RentService> {
        return listServices
    }

    override fun getItemCount(): Int = listServices.size

    override fun getServiceAt(index: Int): RentService = listServices[index]

}