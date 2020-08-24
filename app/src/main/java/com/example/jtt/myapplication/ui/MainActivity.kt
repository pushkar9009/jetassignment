package com.example.jtt.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jtt.myapplication.R
import com.example.jtt.myapplication.datasource.UserDataSource
import com.example.jtt.myapplication.viewmodel.ArticleUserModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.sql.DataSource

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArticlesAdapter()
        val datasource = UserDataSource()
        recycler_view.layoutManager = LinearLayoutManager(this)
        val itemViewModel = ViewModelProviders.of(this).get(ArticleUserModel::class.java)
        itemViewModel.userPagedList.observe(this, Observer {
            adapter.submitList(it)
        })



        datasource?.isLoading?.observe(this, Observer {
            if (it!!) progressBar1?.visibility = View.VISIBLE else progressBar1?.visibility = View.GONE
        })

        recycler_view.adapter = adapter


    }
}
