package com.example.githubuserapp2.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

    @Query("SELECT * from favorite ORDER BY username ASC")
    fun getAllFavorites(): LiveData<List<Favorite>>
}