package com.example.android_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "date")
    val date: String
)