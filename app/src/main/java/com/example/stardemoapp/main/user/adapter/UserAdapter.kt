package com.example.stardemoapp.main.user.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stardemoapp.base.BaseAdapter
import com.example.stardemoapp.databinding.ItemUserBinding
import com.example.stardemoapp.extensions.loadImageFromURL
import com.example.stardemoapp.main.user.model.ListUserResponse.ListUserResponseItem

class UserAdapter: BaseAdapter<ListUserResponseItem>() {

    var onUserClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return DataViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class DataViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root), Binder<ListUserResponseItem> {

        @SuppressLint("SetTextI18n")
        override fun bind(item: ListUserResponseItem, position: Int) {
            binding.apply {
                loadImageFromURL(context, item.avatarUrl, imgAvaUser, true)
                tvUserName.text = item.login
                itemUser.setOnClickListener {
                    onUserClick?.invoke(item.login)
                }
            }
        }
    }
}