package com.openwebinars.filmapp.model

import com.openwebinars.filmapp.FilmApp
import com.openwebinars.filmapp.data.database.FilmDao
import com.openwebinars.filmapp.data.database.FilmEntity

class FavoritesRepository {

    private val database: FilmDao = FilmApp.instance.room.filmDao()

    suspend fun get(): List<Film> = database.get().toFilms()

    suspend fun save(film: Film) {
        database.insert(film.toEntity())
    }

    suspend fun exist(film: Film): Boolean =
        database.getById(film.id) != null

    suspend fun remove(film: Film) {
        database.removeById(film.id)
    }

    private fun Film.toEntity(): FilmEntity =
        FilmEntity(
            id = this.id,
            name = this.name,
            director = this.director,
            rate = this.rate,
            image = this.image,
            synopsis = this.synopsis
        )

    private fun FilmEntity.toFilm(): Film =
        Film(
            id = this.id,
            name = this.name,
            director = this.director,
            rate = this.rate,
            image = this.image,
            synopsis = this.synopsis
        )

    private fun List<FilmEntity>.toFilms(): List<Film> =
        this.map { it.toFilm() }
}