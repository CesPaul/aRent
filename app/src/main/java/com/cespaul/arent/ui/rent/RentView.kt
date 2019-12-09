package com.cespaul.arent.ui.rent

import com.cespaul.arent.base.BaseView

interface RentView : BaseView {

    fun updateMonthList()

    fun updateRentList()

    fun showAddMonthDialog()

    fun showAddRentDialog()

}