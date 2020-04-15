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
        holder.nameView?.text = dbContent[position].name
        holder.timeView?.text = dbContent[position].time
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var idView: TextView? = null
        var nameView : TextView? = null
        var timeView : TextView? = null
        init{
            idView = itemView.findViewById(R.id.id_list_item)
            nameView = itemView.findViewById(R.id.name_list_item)
            timeView = itemView.findViewById(R.id.time_list_item)
        }
    }
}