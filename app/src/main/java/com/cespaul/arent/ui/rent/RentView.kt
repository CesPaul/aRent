package com.cespaul.arent.ui.rent

import com.cespaul.arent.base.BaseView

interface RentView : BaseView {

    fun loadRentList()

    fun updateRentList()

    fun onAddService()

    fun onEditService(position: Int)

    fun onDeleteService(position: Int)
}
