package com.example.githubuserapp2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp2.api.ApiConfig
import com.example.githubuserapp2.response.ResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel: ViewModel() {

    private val _followersUser = MutableLiveData<List<ResponseItem>>()
    val followersUser : LiveData<List<ResponseItem>> = _followersUser

    private fun getFollowers() {
        val client = ApiConfig.getApiService().followUser(USERNAME, "followers")
        client.enqueue(object : Callback<List<ResponseItem>> {
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: Response<List<ResponseItem>>
            ) {
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _followersUser.value = responseBody!!
                    }
                }
                else {
                    Log.e(TAG, "onResponse: ${response.message()}", )
                }
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                Log.e(TAG, "onResponse: ${t.message}", )
            }

        })
    }

    fun setUsername(username: String){
        USERNAME = username

        getFollowers()
    }


    companion object {
        private const val TAG = "FollowerslViewModel"
        private var USERNAME = "username"
    }
}