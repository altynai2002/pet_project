package com.pro.petproject.ui.main.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pro.petproject.data.models.PostEntity
import com.pro.petproject.databinding.ItemRecycleBinding

class ItemViewHolder(private val binding: ItemRecycleBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var postId: String = ""
    //    bind создается здесь чтобы не вызывать по 1000раз
    fun bind(item: PostEntity) {
        with(binding){
            itemTitle.text = item.title
            itemText.text = item.content
            postId = item.objectId
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: ItemAdapter.Listener): ItemViewHolder {
            val binding = ItemRecycleBinding.inflate(LayoutInflater.from(parent.context))

            return ItemViewHolder(binding).apply {
                itemView.setOnClickListener {
                    listener.onClick(postId)
                }
            }
        }
    }
}