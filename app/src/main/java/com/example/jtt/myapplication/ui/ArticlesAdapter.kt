package com.example.jtt.myapplication.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jtt.myapplication.R
import com.example.jtt.myapplication.model.ArticleResponse
import com.example.jtt.myapplication.util.DateUtil
import com.example.jtt.myapplication.util.NumberFormatter
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.item_articles.view.*
import java.text.DecimalFormat




class ArticlesAdapter : PagedListAdapter<ArticleResponse, ArticlesAdapter.UserViewHolder>(USER_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_articles, parent, false)


        return UserViewHolder(view)


    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val articles = getItem(position)
        articles?.let { holder.bind(articles) }
    }


    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val articleDate = view.tv_artcile_date
        private val userName = view.tv_user_name
        private val userDesignation = view.tv_user_designation
        private val userImage = view.iv_user_avatar
        private val articleImage = view.iv_article_image
        private val articleContent = view.tv_article_content
        private val articleTitle = view.tv_article_title
        private val articleUrl = view.tv_article_url
        private val articleLikes = view.tv_article_likes
        private val articleComments = view.tv_article_comments

        val dateUtil  = DateUtil()
        val numFormatter = NumberFormatter()

        fun bind(article : ArticleResponse) {
            val formatter = SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)


            userName.text = article.user!![0].name
            userDesignation.text = article.user!![0].designation
            articleContent.text = article.content
            articleTitle.text = article.media?.getOrNull(0)?.title
            articleUrl.text =  article.media?.getOrNull(0)?.url
           // articleComments.text = article.comments.toString().plus(" Comments")
            articleComments.text = numFormatter.formatNumbers(article.comments.toDouble()).plus(" Comments")
            //articleLikes.text = article.likes.toString().plus(" Likes")
            articleLikes.text = numFormatter.formatNumbers(article.likes.toDouble()).plus(" Likes")

            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale("es","ES")).parse(
                article.createdAt)

            articleDate.text = dateUtil.getTimeAgo(date)

            Glide.with(userImage.context)
                .load(article.user!![0].avatar)
                .placeholder(R.drawable.vector_avatar)
                .into(userImage)

            if(article.media?.getOrNull(0) != null) {
                Glide.with(articleImage.context)
                    .load(article.media!![0].image)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(articleImage)
            }else{
                articleImage.visibility = View.GONE
            }
        }


    }


    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<ArticleResponse>() {
            override fun areItemsTheSame(oldItem: ArticleResponse, newItem: ArticleResponse): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ArticleResponse, newItem: ArticleResponse): Boolean =
                newItem == oldItem

        }
    }






}