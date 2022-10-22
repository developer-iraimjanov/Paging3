package com.example.paging3.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3.data.repository.Repository
import com.example.paging3.model.UnsplashImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun searchHeroes(query: String, requireActivity: FragmentActivity) {
        viewModelScope.launch {
            repository.searchImage(query = query).cachedIn(viewModelScope)
                .observe(requireActivity) {
                    response.value = it
                }
        }
    }

    val response = MutableLiveData<PagingData<UnsplashImage>>(PagingData.empty())

}