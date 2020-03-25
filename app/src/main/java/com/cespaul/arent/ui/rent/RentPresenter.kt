package com.cespaul.arent.ui.rent

import com.cespaul.arent.base.BasePresenter
import com.cespaul.arent.model.RentService
import com.cespaul.arent.model.repository.RentRepositoryImpl
import com.cespaul.arent.ui.rent.ui.RentAdapter

class RentPresenter(rentView: RentView) : BasePresenter<RentView>(rentView) {

    private val rentRepository = RentRepositoryImpl()
    val rentAdapter = RentAdapter(
        view.getContext(),
        RentRepositoryImpl(),
        { position, item ->
            onEditService(item)
        },
        { position, item ->
            onDeleteService(item)
        }
    )

    fun onAddService() {
        view.showAddDialog {
            rentRepository.addService(it)
            rentAdapter.notifyDataSetChanged()
        }
    }

    private fun onDeleteService(itemService: RentService) {
        view.showDeleteDialog {
            rentRepository.deleteService(itemService)
            rentAdapter.notifyDataSetChanged()
        }
    }

    private fun onEditService(itemService: RentService) {
        view.showEditDialog(itemService) {
            try {
                itemService.nameService = it.nameService
                itemService.rateService = it.rateService
                itemService.amtService = it.amtService
                itemService.sumService = it.sumService
                rentAdapter.notifyDataSetChanged()
            } catch (e: NumberFormatException) {
                view.showToast("Поля не должны быть пустыми!")
            }
        }
    }

}