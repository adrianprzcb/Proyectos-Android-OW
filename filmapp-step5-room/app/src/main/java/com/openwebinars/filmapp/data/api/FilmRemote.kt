package com.openwebinars.filmapp.data.api

import com.google.gson.annotations.SerializedName

data class FilmRemote(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("synopsis")
    val synopsis: String
)