package com.cespaul.arent.ui.rent

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.TextView
import com.cespaul.arent.R
import com.cespaul.arent.base.BasePresenter
import com.cespaul.arent.model.RentService
import com.cespaul.arent.model.repository.RentRepositoryImpl
import com.cespaul.arent.ui.rent.ui.RentAdapter
import kotlinx.android.synthetic.main.dialog_add_service.view.*
import kotlinx.android.synthetic.main.dialog_delete_service.view.*
import kotlinx.android.synthetic.main.dialog_edit_service.*
import kotlinx.android.synthetic.main.dialog_edit_service.view.*

class RentPresenter(rentView: RentView) : BasePresenter<RentView>(rentView) {

    private val rentRepository = RentRepositoryImpl()
    val rentAdapter = RentAdapter(
        view.getContext(),
        RentRepositoryImpl(),
        { position, item ->
            onEditService(position)
        },
        { position, item ->
            onDeleteService(position)
        }
    )


    fun onAddService() {
        val addDialogView =
            LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_add_service, null)

        val addDialogBuilder = AlertDialog.Builder(view.getContext())
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
            rentAdapter.notifyDataSetChanged()
        }
    }

    private fun onDeleteService(position: Int) {
        val deleteDialogView =
            LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_delete_service, null)

        val deleteDialogBuilder = AlertDialog.Builder(view.getContext())
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
            rentAdapter.notifyDataSetChanged()
        }
    }

    private fun onEditService(position: Int) {
        val editDialogView =
            LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_edit_service, null)

        val editDialogBuilder = AlertDialog.Builder(view.getContext())
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
            rentAdapter.notifyDataSetChanged()
        }
    }

}