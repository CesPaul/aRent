package com.cespaul.arent.ui.rent

import android.os.Bundle
import com.cespaul.arent.R
import com.cespaul.arent.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

class RentActivity : BaseActivity<RentPresenter>(), RentView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_actionbar)
    }

    override fun updateMonthList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateRentList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAddMonthDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAddRentDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun instantiatePresenter(): RentPresenter {
        return RentPresenter(this)
    }
}
