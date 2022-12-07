package com.openwebinars.filmapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmDao {

    @Query("SELECT * FROM FAVORITES")
    suspend fun get(): List<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(film: FilmEntity)

    @Query("SELECT * FROM FAVORITES WHERE id = :filmId")
    suspend fun getById(filmId: Int): FilmEntity?

    @Query("DELETE FROM FAVORITES WHERE id = :filmId")
    suspend fun removeById(filmId: Int)
}