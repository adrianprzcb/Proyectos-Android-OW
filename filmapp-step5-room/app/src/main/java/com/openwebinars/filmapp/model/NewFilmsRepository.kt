package com.openwebinars.filmapp.model

import com.openwebinars.filmapp.data.api.FilmRemote

class NewFilmsRepository {

    private val api = FilmsService()

    suspend fun get(): List<Film>  = api.get().toFilms()

    private fun FilmRemote.toFilm(): Film =
        Film(
            id = this.id,
            name = this.name,
            director = this.director,
            rate = this.rate,
            image = this.image,
            synopsis = this.synopsis
        )

    private fun List<FilmRemote>.toFilms(): List<Film> =
        this.map { it.toFilm() }
}