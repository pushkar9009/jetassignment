package com.example.jtt.myapplication.model

import com.squareup.moshi.Json

class User(){
    @Json(name = "id")
    var id: String? = null

    @Json(name = "blogId")
    var blogId: String? = null

    @Json(name = "createdAt")
    var createdAt: String? = null

    @Json(name = "name")
    var name: String? = null

    @Json(name = "avatar")
    var avatar: String? = null

    @Json(name = "lastname")
    var lastname: String? = null

    @Json(name = "city")
    var city: String? = null

    @Json(name = "designation")
    var designation: String? = null

    @Json(name = "about")
    var about: String? = null


}