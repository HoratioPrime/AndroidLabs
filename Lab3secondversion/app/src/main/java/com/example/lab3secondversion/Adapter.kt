package com.example.lab3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val dbContent: List<Record>): RecyclerView.Adapter<Adapter.ViewHolder>(){

    override fun getItemCount() = dbContent.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idView?.text = dbContent[position].id.toString()
        holder.lastNameView?.text = dbContent[position].lastName
        holder.firstNameView?.text = dbContent[position].firstName
        holder.patronymicView?.text = dbContent[position].patronymic
        holder.timeView?.text = dbContent[position].time
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var idView: TextView? = null
        var lastNameView : TextView? = null
        var firstNameView : TextView? = null
        var patronymicView : TextView? = null
        var timeView : TextView? = null
        init{
            idView = itemView.findViewById(R.id.id_list_item)
            lastNameView = itemView.findViewById(R.id.lastName_list_item)
            firstNameView = itemView.findViewById(R.id.firstName_list_item)
            patronymicView = itemView.findViewById(R.id.patronymic_list_item)
            timeView = itemView.findViewById(R.id.time_list_item)
        }
    }
}