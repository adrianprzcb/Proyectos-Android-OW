package com.openwebinars.filmapp

import android.app.Application
import androidx.room.Room
import com.openwebinars.filmapp.data.database.FilmDb

class FilmApp : Application() {

    companion object {
        lateinit var instance: FilmApp
            private set
    }

    lateinit var room : FilmDb

    override fun onCreate() {
        super.onCreate()
        room = Room
            .databaseBuilder(this, FilmDb::class.java, "films")
            .build()
        instance = this
    }
}