package com.openwebinars.filmapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FAVORITES")
data class FilmEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "director")
    val director: String,

    @ColumnInfo(name = "rate")
    val rate: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "synopsis")
    val synopsis: String

)