package com.example.githubuserapp2.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserapp2.datastore.SettingPreference
import com.example.githubuserapp2.viewmodel.SettingViewModel

class ViewModelFactory(private  val pref: SettingPreference): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SettingViewModel::class.java)){
            return SettingViewModel(pref) as T
        }
        throw IllegalAccessException("Unknow ViewModel class: " + modelClass.name)
    }
}