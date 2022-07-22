package com.example.roomapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.model.InventoryItem
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var inventoryItemList = emptyList<InventoryItem>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
       return inventoryItemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = inventoryItemList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.Name_txt.text = currentItem.name
        holder.itemView.price_huf_txt.text = currentItem.price_huf.toString()
        holder.itemView.price_eur_txt.text = currentItem.price_eur.toString()

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(inventoryItems: List<InventoryItem>){
        this.inventoryItemList = inventoryItems
        notifyDataSetChanged()
    }
}