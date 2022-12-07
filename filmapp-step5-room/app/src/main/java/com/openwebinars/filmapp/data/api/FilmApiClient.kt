package com.openwebinars.filmapp.data.api

import retrofit2.Response
import retrofit2.http.GET

interface FilmApiClient {

    @GET("films/-Mk8-Ge1Bs8EgrWMM4uV.json")
    suspend fun getNewFilms(): Response<List<FilmRemote>>

}