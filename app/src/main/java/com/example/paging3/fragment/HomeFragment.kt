package com.example.paging3.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import com.example.paging3.R
import com.example.paging3.adapter.UnsplashPagingAdapter
import com.example.paging3.databinding.FragmentHomeBinding
import com.example.paging3.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var unsplashPagingAdapter = UnsplashPagingAdapter()
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.imageSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        binding.rvPhoto.adapter = unsplashPagingAdapter

        viewModel.getAllPhoto.observe(requireActivity()) {
            unsplashPagingAdapter.submitData(lifecycle, it)
        }

        return binding.root
    }

}