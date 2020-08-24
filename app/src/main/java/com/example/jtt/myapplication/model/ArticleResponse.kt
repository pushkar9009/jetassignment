package com.example.jtt.myapplication.model

import com.squareup.moshi.Json

class ArticleResponse {
    @Json(name = "id")
    var id: String? = null

    @Json(name = "createdAt")
    var createdAt: String? = null

    @Json(name = "content")
    var content: String? = null

    @Json(name = "comments")
    var comments: Int = 0

    @Json(name = "likes")
    var likes: Int = 0

    @Json(name = "media")
    var media: List<Media>? = null

    @Json(name = "user")
    var user: List<User>? = null
}