package com.cespaul.arent.ui.rent

import com.cespaul.arent.base.BaseView

interface RentView : BaseView {

    fun loadRentList()

    fun updateRentList()

    fun addService()

    fun editService()

    fun deleteService(position: Int)
}
