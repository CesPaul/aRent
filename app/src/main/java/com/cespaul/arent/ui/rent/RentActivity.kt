package com.cespaul.arent.ui.rent

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.cespaul.arent.R
import com.cespaul.arent.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class RentActivity : BaseActivity<RentPresenter>(), RentView {

    private val layoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_actionbar)
        val addFab = fab

        rentRecycler.layoutManager = layoutManager
        rentRecycler.adapter = presenter.rentAdapter

        addFab.setOnClickListener {
            presenter.onAddService()
        }
    }

    override fun loadRentList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateRentList() {
        presenter.rentAdapter.notifyDataSetChanged()
    }

    override fun instantiatePresenter(): RentPresenter {
        return RentPresenter(this)
    }
}
