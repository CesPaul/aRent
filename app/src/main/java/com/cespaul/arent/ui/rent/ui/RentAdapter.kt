package com.cespaul.arent.ui.rent.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cespaul.arent.R
import com.cespaul.arent.model.RentServices
import kotlinx.android.synthetic.main.service_row.view.*

class RentAdapter(private val context: Context) :
    RecyclerView.Adapter<RentAdapter.ServiceHolder>() {

    private var rentList: List<RentServices> = listOf(
        RentServices("Вода", 5, 2),
        RentServices("Свет", 3, 60)
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
        return rentList.size
    }

    override fun onBindViewHolder(holder: ServiceHolder, position: Int) {
        val rentServices = rentList[position]

        holder.serviceName.text = rentServices.nameService
        holder.rateVal.text = rentServices.rateService.toString()
        holder.amtVal.text = rentServices.amtService.toString()
    }

    class ServiceHolder(itemLayoutView: View) :
        RecyclerView.ViewHolder(itemLayoutView) {
        var serviceName: TextView = itemLayoutView.serviceTextView
        var rateVal: TextView = itemLayoutView.rateTextView
        var amtVal: TextView = itemLayoutView.amtTextView
    }
}