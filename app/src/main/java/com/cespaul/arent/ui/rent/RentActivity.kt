package com.cespaul.arent.ui.rent

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cespaul.arent.R
import com.cespaul.arent.base.BaseActivity
import com.cespaul.arent.model.RentService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_service.view.*
import kotlinx.android.synthetic.main.dialog_delete_service.view.*
import kotlinx.android.synthetic.main.dialog_edit_service.*
import kotlinx.android.synthetic.main.dialog_edit_service.view.*
import kotlinx.android.synthetic.main.toolbar.*


class RentActivity : BaseActivity<RentPresenter>(), RentView {

    private lateinit var toast: Toast

    private val layoutManager = LinearLayoutManager(this)

    private val filter: InputFilter = object : InputFilter {
        val REGEX = "^[0-9.]+$"
        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            return if (source.isEmpty() || source.toString().matches(REGEX.toRegex())) null else ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast = Toast.makeText(applicationContext, "", Toast.LENGTH_LONG)
        setSupportActionBar(toolbar_actionbar)

        rentRecycler.layoutManager = layoutManager
        rentRecycler.adapter = presenter.rentAdapter
        val dividerItemDecoration = DividerItemDecoration(
            rentRecycler.context,
            layoutManager.orientation
        )
        rentRecycler.addItemDecoration(dividerItemDecoration)
        updateCounterSum()

        add_service_button.setOnClickListener {
            presenter.onAddService()
        }
    }

    override fun showAddDialog(onConfirmListener: (RentService) -> Unit) {
        val addDialogView =
            LayoutInflater.from(this).inflate(R.layout.dialog_add_service, null)
        val addDialogBuilder = AlertDialog.Builder(this)
            .setView(addDialogView)
            .setTitle(R.string.add_dialog_title)
            .setCancelable(false)
        val addAlertDialog = addDialogBuilder.show()

        addDialogView.add_rate_service.filters = arrayOf(filter)
        addDialogView.add_amt_service.filters = arrayOf(filter)

        addDialogView.cancelAddButton.setOnClickListener {
            addAlertDialog.dismiss()
        }
        addDialogView.confirmAddButton.setOnClickListener {
            if (addDialogView.add_name_service.text.toString().isEmpty()) {
                showToast("Поле с названием услуги не должно быть пустым!")
                return@setOnClickListener
            }
            val name = addDialogView.add_name_service.text.toString()
            try {
                val rate = addDialogView.add_rate_service.text.toString().toFloat()
                val amt = addDialogView.add_amt_service.text.toString().toFloat()
                val sum = rate * amt
                val serviceRent = RentService(0, name, rate, amt, sum)
                addAlertDialog.dismiss()
                onConfirmListener.invoke(serviceRent)
            } catch (e: NumberFormatException) {
                showToast("Проверьте корректность ввода. Также поля ввода не должны быть пустыми!")
                return@setOnClickListener
            }
        }
    }


    override fun showDeleteDialog(onConfirmListener: () -> Unit) {
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
            onConfirmListener.invoke()
        }
    }

    override fun showEditDialog(
        rentService: RentService,
        onConfirmListener: (RentService) -> Unit
    ) {
        val editDialogView =
            LayoutInflater.from(this).inflate(R.layout.dialog_edit_service, null)

        val editDialogBuilder = AlertDialog.Builder(this)
            .setView(editDialogView)
            .setTitle(R.string.edit_dialog_title)
            .setCancelable(false)
        val editAlertDialog = editDialogBuilder.show()
        editDialogView.edit_rate_service.filters = arrayOf(filter)
        editDialogView.edit_amt_service.filters = arrayOf(filter)

        editAlertDialog.edit_name_service.setText(
            rentService.nameService,
            TextView.BufferType.EDITABLE
        )
        editAlertDialog.edit_rate_service.setText(
            rentService.rateService.toString(),
            TextView.BufferType.EDITABLE
        )
        editAlertDialog.edit_amt_service.setText(
            rentService.amountService.toString(),
            TextView.BufferType.EDITABLE
        )

        editDialogView.cancelEditButton.setOnClickListener {
            editAlertDialog.dismiss()
        }
        editDialogView.confirmEditButton.setOnClickListener {
            if (editDialogView.edit_name_service.text.toString().isEmpty()) {
                showToast("Поле с названием услуги не должно быть пустым!")
                return@setOnClickListener
            }
            val nameService = editDialogView.edit_name_service.text.toString()
            try {
                val rateService = editDialogView.edit_rate_service.text.toString().toFloat()
                val amtService = editDialogView.edit_amt_service.text.toString().toFloat()
                val sum = rateService * amtService
                val tempRentService = RentService(-1, nameService, rateService, amtService, sum)
                editAlertDialog.dismiss()
                onConfirmListener.invoke(tempRentService)
            } catch (e: NumberFormatException) {
                showToast("Проверьте корректность ввода. Также поля ввода не должны быть пустыми!")
                return@setOnClickListener
            }
            editAlertDialog.dismiss()
        }
    }

    override fun updateCounterSum() {
        counterSum.text = presenter.onCountSumServices().toString()
    }

    override fun showToast(message: String?) {
        toast.setText(message)
        toast.show()
    }

    override fun instantiatePresenter(): RentPresenter {
        return RentPresenter(this)
    }

}
