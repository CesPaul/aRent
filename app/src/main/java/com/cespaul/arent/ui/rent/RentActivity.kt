package com.cespaul.arent.ui.rent

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cespaul.arent.R
import com.cespaul.arent.base.BaseActivity
import com.cespaul.arent.model.RentService
import com.cespaul.arent.model.repository.RentRepositoryImpl
import com.cespaul.arent.ui.rent.ui.RentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_service.view.*
import kotlinx.android.synthetic.main.toolbar.*

class RentActivity : BaseActivity<RentPresenter>(), RentView {
    private val rentAdapter = RentAdapter(
        this,
        RentRepositoryImpl(),
        { _, item ->
            Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show()
        },
        { position, item ->
            deleteService(position)
        }
    )
    private val layoutManager = LinearLayoutManager(this)
    private val rentRepository = RentRepositoryImpl()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_actionbar)
        val addFab = fab

        rentRecycler.layoutManager = layoutManager
        rentRecycler.adapter = rentAdapter

        addFab.setOnClickListener {
            addService()
        }
    }

    override fun addService() {
        val addDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_service, null)

        val addDialogBuilder = AlertDialog.Builder(this)
            .setView(addDialogView)
            .setTitle(R.string.add_dialog_title)
        val addAlertDialog = addDialogBuilder.show()

        addDialogView.cancelAddButton.setOnClickListener {
            addAlertDialog.dismiss()
        }
        addDialogView.confirmAddButton.setOnClickListener {
            addAlertDialog.dismiss()
            val name = addDialogView.name_service.text.toString()
            val rate = addDialogView.rate_service.text.toString().toFloat()
            val amt = addDialogView.amt_service.text.toString().toFloat()

            val sum = rate * amt
            val serviceRent = RentService(0, name, rate, amt, sum)
            rentRepository.addService(serviceRent)
            rentAdapter.updateList()
        }
    }

    override fun deleteService(position: Int) {
        val deleteDialogView =
            LayoutInflater.from(this).inflate(R.layout.dialog_delete_service, null)

        val deleteDialogBuilder = AlertDialog.Builder(this)
            .setView(deleteDialogView)
            .setTitle(R.string.delete_dialog_title)
        val deleteAlertDialog = deleteDialogBuilder.show()

        deleteDialogView.cancelAddButton.setOnClickListener {
            deleteAlertDialog.dismiss()
        }
        deleteDialogView.confirmAddButton.setOnClickListener {
            deleteAlertDialog.dismiss()
            val itemService = rentRepository.getServiceAt(position)
            rentRepository.deleteService(itemService)
            rentAdapter.updateList()
        }
    }

    override fun editService() {
        TODO("Not yet implemented")
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
