package com.example.android_app.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_app.model.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<Event>)

    @Query("SELECT * FROM events")
    fun getAllEvents(): Flow<List<Event>>
}