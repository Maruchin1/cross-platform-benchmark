package com.example.android_app.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_app.model.Event

@Database(
    entities = [
        Event::class
    ],
    version = 1
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun dao(): LocalDatabaseDao
}