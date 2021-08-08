package com.example.stardemoapp.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: MutableList<T> = ArrayList()
    var onClickListener: ((position: Int) -> Unit)? = null
    var deleteFunction: ((position: Int)-> Unit)? = null
    lateinit var context: Context

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(data[position], position)
        holder.itemView.setOnClickListener { onClickListener?.invoke(position) }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    open fun addData(list: List<T>) {
        data.addAll(list)
        notifyDataSetChanged()
    }

    open fun updateData(list: List<T>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }


    open fun getItem(position: Int): T{
        return data[position]
    }

    fun removeItem(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    fun removeAll() {
        data.clear()
        notifyDataSetChanged()
    }

    internal interface Binder<T> {
        fun bind(item: T, position: Int)
    }
}