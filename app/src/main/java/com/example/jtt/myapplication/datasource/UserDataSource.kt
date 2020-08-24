package com.example.jtt.myapplication.datasource


import androidx.paging.PageKeyedDataSource
import mvvm.f4wzy.com.samplelogin.network.BackEndApi
import mvvm.f4wzy.com.samplelogin.network.WebServiceClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.MutableLiveData
import com.example.jtt.myapplication.model.ArticleResponse









class UserDataSource : PageKeyedDataSource<Int, ArticleResponse>() {
    var isLoading = MutableLiveData<Boolean>()


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ArticleResponse>
    ) {
        isLoading.postValue(true)
        val service = WebServiceClient.client.create(BackEndApi::class.java)
        val call = service.getArticles(FIRST_PAGE.toString(),"10")

       call.enqueue(object : Callback<List<ArticleResponse>>{
           override fun onFailure(call: Call<List<ArticleResponse>>, t: Throwable) {
               isLoading.postValue(false)
           }

           override fun onResponse(call: Call<List<ArticleResponse>>, response: Response<List<ArticleResponse>>) {
               if(response.isSuccessful){
                   val apiResponse = response.body()!!
                   val responseItems = apiResponse

                   isLoading.postValue(false)

                   responseItems?.let {
                       callback.onResult(responseItems,null, FIRST_PAGE+1)
                   }
               }

           }

       })

    }





    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleResponse>) {
        val service = WebServiceClient.client.create(BackEndApi::class.java)
        val call = service.getArticles(params.key.toString(),"10")
        isLoading.postValue(true)

        call.enqueue(object : Callback<List<ArticleResponse>>{
            override fun onFailure(call: Call<List<ArticleResponse>>, t: Throwable) {
                isLoading.postValue(false)

            }

            override fun onResponse(call: Call<List<ArticleResponse>>, response: Response<List<ArticleResponse>>) {
                if(response.isSuccessful){
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse
                    isLoading.postValue(false)

                    val key = params.key + 1

                    responseItems?.let {
                        callback.onResult(responseItems,key)
                    }
                }

            }

        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleResponse>) {
        val service = WebServiceClient.client.create(BackEndApi::class.java)
        val call = service.getArticles(params.key.toString(),"10")

        call.enqueue(object : Callback<List<ArticleResponse>>{
            override fun onFailure(call: Call<List<ArticleResponse>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<ArticleResponse>>, response: Response<List<ArticleResponse>>) {
                if(response.isSuccessful){
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse

                    val key = if (params.key >1 )params.key -1 else 0


                    responseItems?.let {
                        callback.onResult(responseItems,key)
                    }
                }

            }

        })

    }

    fun getNetworkState(): MutableLiveData<*> {
        return isLoading
    }

    companion object{
        const val PAGE_SIZE = 5
        const val FIRST_PAGE = 1

    }
}