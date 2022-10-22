package com.example.githubuserapp2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp2.api.ApiConfig
import com.example.githubuserapp2.response.DetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    private val _detailUser = MutableLiveData<DetailResponse>()
    val detailUser :  LiveData<DetailResponse> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading


    private fun getDetailUser(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().detailUser(USERNAME)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                _isLoading.value =  false
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _detailUser.value = responseBody
                    }
                }
                else {
                    Log.e(TAG, "onResponse: ${response.message()}", )
                }
            }
            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}", )
            }

        } )
    }

    fun setUsername(username: String){
        USERNAME = username

        getDetailUser()
    }

    companion object {
        private const val TAG = "DetailViewModel"
        private var USERNAME = "username"
    }

}