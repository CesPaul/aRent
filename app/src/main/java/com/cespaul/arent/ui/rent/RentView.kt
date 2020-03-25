package com.cespaul.arent.ui.rent

import com.cespaul.arent.base.BaseView
import com.cespaul.arent.model.RentService

interface RentView : BaseView {

    fun showAddDialog(onConfirmListener: (RentService) -> Unit)

    fun showDeleteDialog(onConfirmListener: () -> Unit)

    fun showEditDialog(
        rentService: RentService,
        onConfirmListener: (RentService) -> Unit
    )

}
