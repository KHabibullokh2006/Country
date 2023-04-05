package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ItemAdapter(var list:MutableList<Item>, var itemInt:ItemInterface) : RecyclerView.Adapter<ItemAdapter.ItemHolder>(), ItemTouchHelperAdapter {

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name:TextView = itemView.findViewById(R.id.count_name)
        var square:TextView = itemView.findViewById(R.id.count_area)
        var img:ImageView = itemView.findViewById(R.id.count_img)
        var item:ConstraintLayout = itemView.findViewById(R.id.item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        var holder = ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))
        return holder
    }


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var item = list[position]
        holder.name.text = item.name
        holder.square.text = item.area
        holder.img.setImageResource(item.img)

        val anim = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_anim)
        holder.itemView.startAnimation(anim)

        holder.item.setOnClickListener {
            itemInt.onClick(item)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ItemInterface{
        fun onClick(item: Item)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if(fromPosition<toPosition){
            for (i in fromPosition until toPosition){
                Collections.swap(list,i , i+1)
            }
        }else{
            for (i in fromPosition downTo  toPosition+1){
                Collections.swap(list,i , i-1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}