package com.cespaul.arent.ui.rent

import com.cespaul.arent.base.BasePresenter
import com.cespaul.arent.model.RentService
import com.cespaul.arent.model.db.RentDatabase
import com.cespaul.arent.model.repository.RentRepositoryImpl
import com.cespaul.arent.ui.rent.ui.RentAdapter
import javax.inject.Inject

class RentPresenter(rentView: RentView) : BasePresenter<RentView>(rentView) {

    @Inject
    lateinit var rentDb: RentDatabase
    private val rentRepository = RentRepositoryImpl(rentDb)

    var rentAdapter = RentAdapter(
        view.getContext(),
        rentRepository,
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
            view.updateCounterSum()
        }
    }

    fun onCountSumServices(): Float {
        return rentRepository.countCommonSum()
    }

    private fun onDeleteService(itemService: RentService) {
        view.showDeleteDialog {
            rentRepository.deleteService(itemService)
            rentAdapter.notifyDataSetChanged()
            view.updateCounterSum()
        }
    }


    private fun onEditService(itemService: RentService) {
        view.showEditDialog(itemService) {
            try {
                itemService.nameService = it.nameService
                itemService.rateService = it.rateService
                itemService.amountService = it.amountService
                itemService.sumService = it.sumService
                rentRepository.editService(itemService)
                rentAdapter.notifyDataSetChanged()
                view.updateCounterSum()
            } catch (e: NumberFormatException) {
                view.showToast("Поля не должны быть пустыми!")
            }
        }
    }
}