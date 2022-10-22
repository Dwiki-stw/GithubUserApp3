package com.example.githubuserapp2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.githubuserapp2.database.Favorite
import com.example.githubuserapp2.repository.FavoriteRepository

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)


    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoriteRepository.getAllFavorites()

    fun insert(favorite: Favorite){
        mFavoriteRepository.insert(favorite)
    }

    fun delete(favorite: Favorite){
        mFavoriteRepository.delete(favorite)
    }

}