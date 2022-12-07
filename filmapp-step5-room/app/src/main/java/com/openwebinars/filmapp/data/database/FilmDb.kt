package com.openwebinars.filmapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FilmEntity::class],
    version = 1
)
abstract class FilmDb : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}