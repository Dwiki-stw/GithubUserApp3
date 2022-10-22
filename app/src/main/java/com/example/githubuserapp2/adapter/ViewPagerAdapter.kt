package com.example.githubuserapp2.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuserapp2.ui.fragment.FollowersFragment
import com.example.githubuserapp2.ui.fragment.FollowingFragment

class ViewPagerAdapter(activity : AppCompatActivity, private val username:String?) : FragmentStateAdapter(activity){

    override fun getItemCount(): Int {
        return  2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position){
            0 -> {
                fragment = FollowersFragment()
                val mBundle = Bundle()
                mBundle.putString("USERNAME", username)
                fragment.arguments = mBundle
            }
            1 -> {
                fragment = FollowingFragment()
                val mBundle = Bundle()
                mBundle.putString("USERNAME", username)
                fragment.arguments = mBundle
            }
        }
        return fragment as Fragment
    }

}