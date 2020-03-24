package com.cespaul.arent.ui.rent

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.cespaul.arent.R
import com.cespaul.arent.base.BaseActivity
import com.cespaul.arent.model.RentService
import com.cespaul.arent.model.repository.RentRepositoryImpl
import com.cespaul.arent.ui.rent.ui.RentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_service.view.*
import kotlinx.android.synthetic.main.dialog_delete_service.view.*
import kotlinx.android.synthetic.main.dialog_edit_service.*
import kotlinx.android.synthetic.main.dialog_edit_service.view.*
import kotlinx.android.synthetic.main.toolbar.*

class RentActivity : BaseActivity<RentPresenter>(), RentView {
    private val rentAdapter = RentAdapter(
        this,
        RentRepositoryImpl(),
        { position, item ->
            onEditService(position)
        },
        { position, item ->
            onDeleteService(position)
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
            onAddService()
        }
    }

    override fun onAddService() {
        val addDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_service, null)

        val addDialogBuilder = AlertDialog.Builder(this)
            .setView(addDialogView)
            .setTitle(R.string.add_dialog_title)
            .setCancelable(false)
        val addAlertDialog = addDialogBuilder.show()

        addDialogView.cancelAddButton.setOnClickListener {
            addAlertDialog.dismiss()
        }
        addDialogView.confirmAddButton.setOnClickListener {
            addAlertDialog.dismiss()
            val name = addDialogView.add_name_service.text.toString()
            val rate = addDialogView.add_rate_service.text.toString().toFloat()
            val amt = addDialogView.add_amt_service.text.toString().toFloat()

            val sum = rate * amt
            val serviceRent = RentService(0, name, rate, amt, sum)
            rentRepository.addService(serviceRent)
            rentAdapter.updateList()
        }
    }

    override fun onDeleteService(position: Int) {
        val deleteDialogView =
            LayoutInflater.from(this).inflate(R.layout.dialog_delete_service, null)

        val deleteDialogBuilder = AlertDialog.Builder(this)
            .setView(deleteDialogView)
            .setTitle(R.string.delete_dialog_title)
            .setCancelable(false)
        val deleteAlertDialog = deleteDialogBuilder.show()

        deleteDialogView.cancelDeleteButton.setOnClickListener {
            deleteAlertDialog.dismiss()
        }
        deleteDialogView.confirmDeleteButton.setOnClickListener {
            deleteAlertDialog.dismiss()
            val itemService = rentRepository.getServiceAt(position)
            rentRepository.deleteService(itemService)
            rentAdapter.updateList()
        }
    }

    override fun onEditService(position: Int) {
        val editDialogView =
            LayoutInflater.from(this).inflate(R.layout.dialog_edit_service, null)

        val editDialogBuilder = AlertDialog.Builder(this)
            .setView(editDialogView)
            .setTitle(R.string.edit_dialog_title)
            .setCancelable(false)
        val editAlertDialog = editDialogBuilder.show()

        val itemService = rentRepository.getServiceAt(position)
        editAlertDialog.edit_name_service.setText(
            itemService.nameService,
            TextView.BufferType.EDITABLE
        )
        editAlertDialog.edit_rate_service.setText(
            itemService.rateService.toString(),
            TextView.BufferType.EDITABLE
        )
        editAlertDialog.edit_amt_service.setText(
            itemService.amtService.toString(),
            TextView.BufferType.EDITABLE
        )

        editDialogView.cancelEditButton.setOnClickListener {
            editAlertDialog.dismiss()
        }
        editDialogView.confirmEditButton.setOnClickListener {
            editAlertDialog.dismiss()
            val nameService = editDialogView.edit_name_service.text.toString()
            val rateService = editDialogView.edit_rate_service.text.toString().toFloat()
            val amtService = editDialogView.edit_amt_service.text.toString().toFloat()
            val sum = rateService * amtService
            val editableItem = rentRepository.getServiceAt(position)
            editableItem.nameService = nameService
            editableItem.rateService = rateService
            editableItem.amtService = amtService
            editableItem.sumService = sum
            rentAdapter.updateList()
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
