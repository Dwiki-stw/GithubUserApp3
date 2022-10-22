package com.example.githubuserapp2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp2.api.ApiConfig
import com.example.githubuserapp2.response.ItemsItem
import com.example.githubuserapp2.response.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _listUser = MutableLiveData<List<ItemsItem>>()
    val listUser: LiveData<List<ItemsItem>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private fun searchUser() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().searchUser(USERNAME)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _listUser.value = responseBody.items
                    }
                }
                else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun setUsername(username: String){
        USERNAME = username

        searchUser()
    }

    companion object {
        private const val TAG = "MainViewModel"
        private var USERNAME = ""
    }
}