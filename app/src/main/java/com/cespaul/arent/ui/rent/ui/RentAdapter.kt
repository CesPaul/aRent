package com.cespaul.arent.ui.rent.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cespaul.arent.R
import com.cespaul.arent.model.RentService
import com.cespaul.arent.model.repository.RentRepository
import kotlinx.android.synthetic.main.service_row.view.*

class RentAdapter(
    private val context: Context,
    private val servicesRepository: RentRepository,
    val onEditClickListener: (position: Int, item: RentService) -> Unit,
    val onDeleteClickListener: (position: Int, item: RentService) -> Unit
) :
    RecyclerView.Adapter<RentAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        return ServiceViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.service_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = servicesRepository.getItemCount()

    override fun onBindViewHolder(viewHolder: ServiceViewHolder, position: Int) {
        val rentServices = servicesRepository.getServiceAt(position)
        viewHolder.onEditClickListener = View.OnClickListener {
            onEditClickListener(position, rentServices)
        }
        viewHolder.onDeleteClickListener = View.OnClickListener {
            onDeleteClickListener(position, rentServices)
        }
        viewHolder.serviceName.text = rentServices.nameService
        viewHolder.rateVal.text = rentServices.rateService.toString()
        viewHolder.amtVal.text = rentServices.amountService.toString()
        viewHolder.sumVal.text = rentServices.sumService.toString()
    }

    class ServiceViewHolder(itemLayoutView: View) :
        RecyclerView.ViewHolder(itemLayoutView) {
        private var editButton = itemLayoutView.editServiceView
        private var deleteButton = itemLayoutView.deleteServiceView
        var onEditClickListener: View.OnClickListener? = null
        var onDeleteClickListener: View.OnClickListener? = null
        var serviceName: TextView = itemLayoutView.serviceTextView
        var rateVal: TextView = itemLayoutView.rateTextView
        var amtVal: TextView = itemLayoutView.amtTextView
        var sumVal: TextView = itemLayoutView.sumServiceTextView

        init {
            editButton.setOnClickListener {
                onEditClickListener?.onClick(it)
            }
            deleteButton.setOnClickListener {
                onDeleteClickListener?.onClick(it)
            }
        }
    }
}