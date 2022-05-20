package com.pro.petproject.ui.main.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pro.petproject.databinding.ItemRecycleBinding

class ItemViewHolder(private val binding: ItemRecycleBinding
) : RecyclerView.ViewHolder(binding.root) {

    //    bind создается здесь чтобы не вызывать по 1000раз
//    fun bind(item: Post) {
    fun bind(item: String) {
        with(binding){
//            itemTxt.text = item.title
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: ItemAdapter.Listener): ItemViewHolder {
//            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycle, parent)
//            val binding = ItemRecycleBinding.bind(view)
            val binding = ItemRecycleBinding.inflate(LayoutInflater.from(parent.context))

            return ItemViewHolder(binding).apply {
                itemView.setOnClickListener {
                    listener.onClick(adapterPosition)
                }
            }
        }
    }

}