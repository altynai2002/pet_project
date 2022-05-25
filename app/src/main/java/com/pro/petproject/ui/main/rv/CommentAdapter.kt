package com.pro.petproject.ui.main.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pro.petproject.data.models.CommentEntity


class CommentAdapter(private val listener: Listener) : RecyclerView.Adapter<CommentViewHolder>() {
    private var list = arrayListOf<CommentEntity>()

    fun setData(list: List<CommentEntity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder.create(parent,listener)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
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
