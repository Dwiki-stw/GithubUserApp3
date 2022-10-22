package com.example.githubuserapp2.api

import com.example.githubuserapp2.response.DetailResponse
import com.example.githubuserapp2.response.ResponseItem
import com.example.githubuserapp2.response.SearchResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService{
    @GET("search/users")
    @Headers("Authorization: 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")
    fun searchUser(
        @Query("q") query: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: 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")
    fun detailUser(
        @Path("username") username: String,
    ): Call<DetailResponse>

    @GET("users/{username}/{tipe}")
    @Headers("Authorization: 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")
    fun followUser(
        @Path("username") username: String,
        @Path("tipe") tipe: String
    ): Call<List<ResponseItem>>
}