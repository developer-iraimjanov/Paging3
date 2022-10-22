package com.example.paging3.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.paging3.data.local.UnsplashDatabase
import com.example.paging3.data.paging.SearchPagingSource
import com.example.paging3.data.paging.UnsplashRemoteMediator
import com.example.paging3.data.remote.UnsplashApi
import com.example.paging3.model.UnsplashImage
import com.example.paging3.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class Repository @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) {

    fun getAllImages(): LiveData<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllPhotos() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi = unsplashApi,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
    }

    fun searchImage(query: String): LiveData<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(unsplashApi = unsplashApi, query = query)
            }
        ).liveData
    }

}