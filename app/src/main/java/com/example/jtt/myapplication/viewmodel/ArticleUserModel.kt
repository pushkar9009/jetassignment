package com.example.jtt.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.jtt.myapplication.datasource.UserDataSource
import com.example.jtt.myapplication.datasource.UserDataSourceFactory
import com.example.jtt.myapplication.model.ArticleResponse
import androidx.lifecycle.Transformations.switchMap


class ArticleUserModel : ViewModel() {


    val userPagedList: LiveData<PagedList<ArticleResponse>>

    val liveDataSource: LiveData<UserDataSource>

    private var isLoading: LiveData<Boolean>? = null


    init {
        val itemDataSourceFactory = UserDataSourceFactory()

        liveDataSource = itemDataSourceFactory.userLiveDataSource
        val dataSource = UserDataSource()
       // isLoading = itemDataSourceFactory.getMutableLiveData()?.let { switchMap(it, dataSource -> dataSource.getNetworkState())  }

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(UserDataSource.PAGE_SIZE)
            .build()


        userPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()

    }

    fun getisLoading(): LiveData<Boolean>? {
        return isLoading
    }

}