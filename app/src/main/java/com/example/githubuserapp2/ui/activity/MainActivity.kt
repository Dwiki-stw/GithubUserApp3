package com.example.githubuserapp2.ui.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp2.response.ItemsItem
import com.example.githubuserapp2.R
import com.example.githubuserapp2.adapter.ListUsersAdapter
import com.example.githubuserapp2.databinding.ActivityMainBinding
import com.example.githubuserapp2.datastore.SettingPreference
import com.example.githubuserapp2.helper.ViewModelFactory
import com.example.githubuserapp2.viewmodel.MainViewModel
import com.example.githubuserapp2.viewmodel.SettingViewModel
import com.google.android.material.switchmaterial.SwitchMaterial


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.searchUser


        //mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.listUser.observe(this) { listUser ->
            showListUser(listUser)
        }
        mainViewModel.isLoading.observe(this) {show ->
            showLoading(show)
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                mainViewModel.setUsername(query)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        val item = menu!!.findItem(R.id.app_bar_switch)
        item.setActionView(R.layout.switch_item)
        val switchDarkMode = item.actionView.findViewById<SwitchMaterial>(R.id.switch_dark_mode)

        val pref = SettingPreference.getInstance(dataStore)
        val settingViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(SettingViewModel::class.java)

        settingViewModel.getThemaSetting().observe(this){ isDarkMode ->
            if (isDarkMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchDarkMode.isChecked = true
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchDarkMode.isChecked = false
            }
        }

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            settingViewModel.saveThemaSetting(isChecked)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite_menu -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =  null
    }


    private fun showListUser(searchUser : List<ItemsItem>){

        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        val adapter = ListUsersAdapter(searchUser)
        binding.rvUsers.adapter = adapter

        adapter.setOnItemClickCallback(object: ListUsersAdapter.OnItemClickCallback{
            override fun onItemClicked(username: String?) {
                val intent = Intent(this@MainActivity, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.EXTRA_USER, username)
                startActivity(intent)
            }
        } )

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}