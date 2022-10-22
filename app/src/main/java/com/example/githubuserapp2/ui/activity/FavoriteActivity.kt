package com.example.githubuserapp2.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp2.adapter.FavoriteUserAdapter
import com.example.githubuserapp2.database.Favorite
import com.example.githubuserapp2.databinding.ActivityFavoriteBinding
import com.example.githubuserapp2.viewmodel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var mFavoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        mFavoriteViewModel.getAllFavorites().observe(this){ list ->
            if (list != null){
                showListFavorite(list)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showListFavorite(favoriteUser: List<Favorite>){
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        val adapter = FavoriteUserAdapter(favoriteUser)
        binding.rvUsers.adapter = adapter

        adapter.setOnItemClickCallback(object: FavoriteUserAdapter.OnItemClickCallback{
            override fun onItemClicked(username: String) {
                val intent = Intent(this@FavoriteActivity, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.EXTRA_USER, username)
                startActivity(intent)
            }

        })
    }
}

