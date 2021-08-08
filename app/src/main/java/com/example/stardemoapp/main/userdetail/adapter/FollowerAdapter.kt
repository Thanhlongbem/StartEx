package com.example.stardemoapp.main.userdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stardemoapp.base.BaseAdapter
import com.example.stardemoapp.databinding.ItemFollowerBinding
import com.example.stardemoapp.extensions.loadImageFromURL
import com.example.stardemoapp.main.userdetail.model.FollowerResponseItem

class FollowerAdapter: BaseAdapter<FollowerResponseItem>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return DataViewHolder(ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    inner class DataViewHolder(val binding: ItemFollowerBinding): RecyclerView.ViewHolder(binding.root), Binder<FollowerResponseItem>{
        override fun bind(item: FollowerResponseItem, position: Int) {
            item.apply {
                loadImageFromURL(context, this.avatarUrl, binding.imgFollower, true)
                binding.tvFollowerName.text = this.login
            }
        }
    }
}