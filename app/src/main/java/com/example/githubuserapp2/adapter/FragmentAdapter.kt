package com.example.githubuserapp2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp2.databinding.RvUsersBinding
import com.example.githubuserapp2.response.ResponseItem

class FragmentAdapter(private val listFollowers: List<ResponseItem>) : RecyclerView.Adapter<FragmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvUsersBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listFollowers[position])
    }

    override fun getItemCount(): Int {
        return listFollowers.size
    }

    class ViewHolder(private val binding: RvUsersBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(responseItem: ResponseItem){
                binding.apply {
                    tvName.text = responseItem.login
                    tvUrl.text = responseItem.htmlUrl

                    Glide.with(itemView)
                        .load(responseItem.avatarUrl)
                        .into(imgUser)
                }
            }
    }
}