package com.example.android_app.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_app.model.Event

@Database(
    entities = [
        Event::class
    ],
    version = 2
)
@TypeConverters(DatabaseConverter::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun dao(): LocalDatabaseDao
}