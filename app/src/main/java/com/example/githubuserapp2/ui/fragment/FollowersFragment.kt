package com.example.githubuserapp2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp2.adapter.FragmentAdapter
import com.example.githubuserapp2.databinding.FragmentFollowersBinding
import com.example.githubuserapp2.viewmodel.FollowersViewModel
import com.example.githubuserapp2.response.ResponseItem

class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private val followersViewModel: FollowersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString("USERNAME")


        followersViewModel.setUsername(username.toString())
        followersViewModel.followersUser.observe(viewLifecycleOwner){followers ->
            showFollowers(followers)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showFollowers(responseFollowers: List<ResponseItem>){
        val adapter = FragmentAdapter(responseFollowers)
        binding.apply {
            rvFollowers.layoutManager = LinearLayoutManager(context)
            rvFollowers.setHasFixedSize(true)
            rvFollowers.adapter =  adapter
        }
    }

}