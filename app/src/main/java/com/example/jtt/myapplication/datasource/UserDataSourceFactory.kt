package com.example.jtt.myapplication.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.jtt.myapplication.model.ArticleResponse


class UserDataSourceFactory: DataSource.Factory<Int, ArticleResponse>(){

    val userLiveDataSource = MutableLiveData<UserDataSource>()

    private var mutableLiveData: MutableLiveData<UserDataSource>? = null



    override fun create(): DataSource<Int, ArticleResponse> {
        val userDataSource =
            UserDataSource()
            userLiveDataSource.postValue(userDataSource)
            mutableLiveData = MutableLiveData<UserDataSource>()

        return userDataSource

    }

    fun getMutableLiveData(): MutableLiveData<UserDataSource>? {
        return mutableLiveData
    }
}