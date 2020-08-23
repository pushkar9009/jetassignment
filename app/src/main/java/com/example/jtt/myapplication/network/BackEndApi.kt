package mvvm.f4wzy.com.samplelogin.network

import com.example.jtt.myapplication.model.ArticleResponse
import retrofit2.Call
import retrofit2.http.*

interface BackEndApi {
    @GET("api/v1/blogs")
    fun getArticles(@Query("page") page: String, @Query("limit") limit:String): Call<List<ArticleResponse>>

}

