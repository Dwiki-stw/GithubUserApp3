package com.example.githubuserapp2.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuserapp2.R
import com.example.githubuserapp2.adapter.ViewPagerAdapter
import com.example.githubuserapp2.database.Favorite
import com.example.githubuserapp2.databinding.ActivityDetailUserBinding
import com.example.githubuserapp2.viewmodel.DetailViewModel
import com.example.githubuserapp2.response.DetailResponse
import com.example.githubuserapp2.viewmodel.FavoriteViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private val detailViewModel : DetailViewModel by viewModels()
    private lateinit var mFavoriteViewModel: FavoriteViewModel
    private lateinit var user: String
    private lateinit var favorite: Favorite
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getStringExtra(EXTRA_USER).toString()

        val viewPagerAdapter = ViewPagerAdapter(this, user)
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) {tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f


        detailViewModel.setUsername(user)
        detailViewModel.detailUser.observe(this){detailUser ->
            showDetailUser(detailUser)
            createFavorite(detailUser)
        }
        detailViewModel.isLoading.observe(this){show ->
            showLoading(show)
        }

        binding.btnShare.setOnClickListener{
            val messageShare = "Username GitHub: $user\nURL: ${favorite.url}"
            val intentShare = Intent(Intent.ACTION_SEND)
            intentShare.type = "text/plain"
            intentShare.putExtra(Intent.EXTRA_TEXT, messageShare)
            startActivity(intentShare)
        }

        mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        mFavoriteViewModel.getAllFavorites().observe(this){list ->
            isFavorite = checkIsFavorite(list, user)
        }

        binding.btnFavorite.setOnClickListener{
            if (isFavorite){
                it.setBackgroundResource(R.drawable.ic_un_favorite)
                deleteFromDatabase()
                Toast.makeText(this, UN_FAVORITE, Toast.LENGTH_SHORT).show()
            }
            else{
                it.setBackgroundResource(R.drawable.ic_favorite)
                addToDatabase()
                Toast.makeText(this, ADD_FAVORITE, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDetailUser(user: DetailResponse){
        binding.apply {
            tvName.text = user.name
            tvUsername.text = user.login
            tvCompany.text = user.company
            tvLocation.text = user.location
            tvRepository.text = user.publicRepos.toString()
            tvFollowers.text = user.followers.toString()
            tvFollowing.text = user.following.toString()

            Glide.with(this@DetailUserActivity)
                .load(user.avatarUrl)
                .into(imgUser)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun checkIsFavorite(userFavorite: List<Favorite>, username: String) : Boolean{
        var result = false

        for(user in userFavorite){
            if (username == user.username){
                result = true
                break
            }
        }

        if (result){
            binding.btnFavorite.setBackgroundResource(R.drawable.ic_favorite)
        }
        else{
            binding.btnFavorite.setBackgroundResource(R.drawable.ic_un_favorite)
        }

        return result
    }

    private fun addToDatabase(){
        mFavoriteViewModel.insert(favorite)
    }

    private fun deleteFromDatabase(){
        mFavoriteViewModel.delete(favorite)
    }

    private fun createFavorite(detailUser: DetailResponse){
        favorite = Favorite(detailUser.login, detailUser.url, detailUser.avatarUrl)
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
        const val EXTRA_USER = "extra_user"
        const val ADD_FAVORITE= "Add to Favorite"
        const val UN_FAVORITE= "Delete from Favorite"
    }

}