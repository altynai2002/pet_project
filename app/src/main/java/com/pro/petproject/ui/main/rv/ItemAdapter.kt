package com.pro.petproject.ui.main.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val listener: Listener) : RecyclerView.Adapter<ItemViewHolder>() {
//    private var list = arrayListOf<Post>()
    private var list = arrayListOf<String>()

//    fun setData(list: List<Post>) {
    fun setData(list: List<String>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent,listener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    //    перенесли viewholder в отдельный файл
    interface Listener{
        fun onClick(index: Int)
    }
}