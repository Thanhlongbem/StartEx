package com.example.stardemoapp.main.userdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stardemoapp.base.BaseAdapter
import com.example.stardemoapp.databinding.ItemReposBinding
import com.example.stardemoapp.main.userdetail.model.ReposResponseItem

class RepoAdapter: BaseAdapter<ReposResponseItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DataViewHolder(ItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    inner class DataViewHolder(val binding: ItemReposBinding): RecyclerView.ViewHolder(binding.root), Binder<ReposResponseItem>{
        override fun bind(item: ReposResponseItem, position: Int) {
            item.apply {
                binding.tvLanguage.text = this.language
                binding.tvNameRepo.text = this.name
                binding.tvURL.text = this.url
            }
        }
    }
}