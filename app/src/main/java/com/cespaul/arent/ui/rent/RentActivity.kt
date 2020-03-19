package com.cespaul.arent.ui.rent

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cespaul.arent.R
import com.cespaul.arent.base.BaseActivity
import com.cespaul.arent.model.repository.RentRepositoryImpl
import com.cespaul.arent.ui.rent.ui.RentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class RentActivity : BaseActivity<RentPresenter>(), RentView {
    private val rentAdapter = RentAdapter(
        this,
        RentRepositoryImpl(),
        { _, item ->
            Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show()
        },
        { _, item ->
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
        }
    )
    private val layoutManager = LinearLayoutManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_actionbar)
        val addFab = fab

        rentRecycler.layoutManager = layoutManager
        rentRecycler.adapter = rentAdapter
        addFab.setOnClickListener {
            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
        }

    }

    override fun loadRentList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateRentList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun instantiatePresenter(): RentPresenter {
        return RentPresenter(this)
    }
}
