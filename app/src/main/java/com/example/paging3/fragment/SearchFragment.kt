package com.example.paging3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import com.example.paging3.adapter.UnsplashPagingAdapter
import com.example.paging3.databinding.FragmentSearchBinding
import com.example.paging3.util.hideKeyboardFrom
import com.example.paging3.util.onSubmit
import com.example.paging3.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private var unsplashPagingAdapter = UnsplashPagingAdapter()
    private val viewModel: SearchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.edtSearch.onSubmit {
            binding.edtSearch.clearFocus()
            hideKeyboardFrom(requireActivity(), binding.edtSearch)
            val query = binding.edtSearch.text.toString().trim().lowercase()
            if (query.isNotEmpty()) {
                viewModel.searchHeroes(query = query, requireActivity = requireActivity())
            }
        }

        viewModel.response.observe(requireActivity()) {
            unsplashPagingAdapter.submitData(lifecycle, pagingData = it)
        }


        binding.rvPhoto.adapter = unsplashPagingAdapter

        return binding.root
    }
}