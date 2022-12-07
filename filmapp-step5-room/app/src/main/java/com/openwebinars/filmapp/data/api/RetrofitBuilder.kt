package com.openwebinars.filmapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    fun build(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://jetpack-course-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}