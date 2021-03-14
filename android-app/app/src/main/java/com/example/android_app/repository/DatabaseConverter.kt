package com.example.android_app.repository

import androidx.room.TypeConverter

class DatabaseConverter {

    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return list?.joinToString(separator = ",")
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.split(",")
    }
}