package com.example.githubuserapp2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp2.database.Favorite
import com.example.githubuserapp2.databinding.TemplateListUsersBinding

class FavoriteUserAdapter(private val listFavoriteUser: List<Favorite>) : RecyclerView.Adapter<FavoriteUserAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = TemplateListUsersBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listFavoriteUser[position])

        holder.areaClick.setOnClickListener{
            onItemClickCallback.onItemClicked(listFavoriteUser[position].username)
        }
    }

    override fun getItemCount(): Int {
        return listFavoriteUser.size
    }

    class ViewHolder(private val binding: TemplateListUsersBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favoriteUser: Favorite){
            binding.apply {
                tvName.text = favoriteUser.username
                tvUrl.text = favoriteUser.url

                Glide.with(itemView)
                    .load(favoriteUser.urlPhoto)
                    .into(imgUser)
            }
        }
        val areaClick = binding.container
    }

    interface OnItemClickCallback{
        fun onItemClicked(username: String)
    }
}
