package com.openwebinars.filmapp.model

import com.openwebinars.filmapp.data.api.FilmApiClient
import com.openwebinars.filmapp.data.api.FilmRemote
import com.openwebinars.filmapp.data.api.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmsService {

    private val retrofit = RetrofitBuilder.build()

    suspend fun get() : List<FilmRemote> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(FilmApiClient::class.java).getNewFilms()
            response.body() ?: emptyList()
        }
    }
}