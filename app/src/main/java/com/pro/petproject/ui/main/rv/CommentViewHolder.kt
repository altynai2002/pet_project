package com.pro.petproject.ui.main.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pro.petproject.data.models.CommentEntity
import com.pro.petproject.databinding.CommentRecycleBinding

class CommentViewHolder(private val binding: CommentRecycleBinding
) : RecyclerView.ViewHolder(binding.root) {

    //    bind создается здесь чтобы не вызывать по 1000раз
    fun bind(item: CommentEntity) {
        with(binding){
            commentText.text = item.text
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: CommentAdapter.Listener): CommentViewHolder {
            val binding = CommentRecycleBinding.inflate(LayoutInflater.from(parent.context))

            return CommentViewHolder(binding).apply {
                itemView.setOnClickListener {
                    listener.onClick(adapterPosition)
                }
            }
        }
    }
}