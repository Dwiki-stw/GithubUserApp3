package com.example.githubuserapp2.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "url")
    val url: String? = null,

    @ColumnInfo(name = "urlPhoto")
    val urlPhoto: String? = null
): Parcelable