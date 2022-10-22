package com.example.githubuserapp2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp2.datastore.SettingPreference
import kotlinx.coroutines.launch

class SettingViewModel(private val  pref: SettingPreference) : ViewModel() {
    fun getThemaSetting() : LiveData<Boolean>{
        return pref.getThemaSetting().asLiveData()
    }

    fun saveThemaSetting(isDarkModeActive: Boolean){
        viewModelScope.launch {
            pref.saveThemaSetting(isDarkModeActive)
        }
    }
}