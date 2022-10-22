package com.example.githubuserapp2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp2.response.ItemsItem
import com.example.githubuserapp2.databinding.TemplateListUsersBinding


class ListUsersAdapter(private val listUsers: List<ItemsItem>) : RecyclerView.Adapter<ListUsersAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = TemplateListUsersBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(listUsers[position])

        holder.container.setOnClickListener {
            onItemClickCallback.onItemClicked(listUsers[position].login)
        }
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    class ViewHolder(private val binding: TemplateListUsersBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemItems : ItemsItem){

            binding.apply {
                tvName.text = itemItems.login
                tvUrl.text = itemItems.htmlUrl

                Glide.with(itemView)
                    .load(itemItems.avatarUrl)
                    .into(imgUser)
            }
        }
        val container = binding.container
    }

    interface OnItemClickCallback{
        fun onItemClicked(username: String?)
    }

}