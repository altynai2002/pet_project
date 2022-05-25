package com.pro.petproject.ui.main.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pro.petproject.R
import com.pro.petproject.data.models.PostEntity
import com.pro.petproject.databinding.ItemRecycleBinding

class ItemViewHolder(private val binding: ItemRecycleBinding
) : RecyclerView.ViewHolder(binding.root) {

    //    bind создается здесь чтобы не вызывать по 1000раз
    fun bind(item: PostEntity) {
//    fun bind(item: String) {
        with(binding){
            itemTitle.text = item.title
            itemText.text = item.content
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: ItemAdapter.Listener): ItemViewHolder {
            val binding = ItemRecycleBinding.inflate(LayoutInflater.from(parent.context))

            return ItemViewHolder(binding).apply {
                itemView.setOnClickListener {
                    listener.onClick(adapterPosition)
                }
            }
        }
    }
}