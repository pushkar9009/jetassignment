package com.example.jtt.myapplication.model

import com.squareup.moshi.Json

class Media(){
    @Json(name = "id")
    var id: String? = null

    @Json(name = "blogId")
    var blogId: String? = null

    @Json(name = "createdAt")
    var createdAt: String? = null

    @Json(name = "image")
    var image: String? = null

    @Json(name = "title")
    var title: String? = null

    @Json(name = "url")
    var url: String? = null


}