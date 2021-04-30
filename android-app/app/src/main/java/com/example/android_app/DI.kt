package com.example.android_app

import android.content.Context
import androidx.room.Room
import com.example.android_app.repository.LocalDatabase
import com.example.android_app.repository.LocalDatabaseDao
import com.example.android_app.repository.Repository
import com.example.android_app.repository.WebApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.WeakReference

object DI {
    private lateinit var context: WeakReference<Context>

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.143:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val webApi: WebApi by lazy {
        retrofit.create(WebApi::class.java)
    }

    private val localDatabase: LocalDatabase by lazy {
        Room.databaseBuilder(
            context.get()!!,
            LocalDatabase::class.java,
            "local-database"
        ).fallbackToDestructiveMigration().build()
    }

    private val localDatabaseDao: LocalDatabaseDao by lazy {
        localDatabase.dao()
    }

    val repository: Repository by lazy {
        Repository(webApi, localDatabaseDao)
    }

    fun init(context: Context) {
        this.context = WeakReference(context)
    }
}