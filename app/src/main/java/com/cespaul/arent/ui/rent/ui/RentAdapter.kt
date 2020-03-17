package com.cespaul.arent.ui.rent.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cespaul.arent.R
import com.cespaul.arent.model.RentService
import kotlinx.android.synthetic.main.service_row.view.*

class RentAdapter(
    private val context: Context,
    private val onEditClickListener: (position: Int, item: RentService) -> Unit
) :
    RecyclerView.Adapter<RentAdapter.ServiceHolder>() {

    private var servicesList: List<RentService> = listOf(
        RentService("Вода", 5, 2),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 76),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 56),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 54),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 42),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 12),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 32),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 85),
        RentService("Свет", 3, 60),
        RentService("Свет", 3, 60)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHolder {
        return ServiceHolder(
            LayoutInflater.from(context).inflate(
                R.layout.service_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return servicesList.size
    }

    override fun onBindViewHolder(holder: ServiceHolder, position: Int) {
        val rentServices = servicesList[position]
        holder.onEditClickListener = View.OnClickListener {
            onEditClickListener(position, rentServices)
        }
        holder.serviceName.text = rentServices.nameService
        holder.rateVal.text = rentServices.rateService.toString()
        holder.amtVal.text = rentServices.amtService.toString()
    }

    class ServiceHolder(itemLayoutView: View) :
        RecyclerView.ViewHolder(itemLayoutView) {
        var onEditClickListener: View.OnClickListener? = null
        var serviceName: TextView = itemLayoutView.serviceTextView
        var rateVal: TextView = itemLayoutView.rateTextView
        var amtVal: TextView = itemLayoutView.amtTextView

        init {
            itemLayoutView.setOnClickListener {
                onEditClickListener?.onClick(it)
            }
        }
    }
}