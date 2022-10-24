package com.example.githubuserapp2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp2.adapter.FragmentAdapter
import com.example.githubuserapp2.databinding.FragmentFollowingBinding
import com.example.githubuserapp2.viewmodel.FollowingViewModel
import com.example.githubuserapp2.response.ResponseItem

class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private val followingViewModel: FollowingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString("USERNAME")


        followingViewModel.setUsername(username.toString())
        followingViewModel.followingsUser.observe(viewLifecycleOwner){following ->
            showFollowing(following)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun showFollowing(responseFollowing: List<ResponseItem>){
        val adapter = FragmentAdapter(responseFollowing)
        binding.apply {
            rvFollowing.layoutManager = LinearLayoutManager(context)
            rvFollowing.setHasFixedSize(true)
            rvFollowing.adapter =  adapter
        }
    }

}