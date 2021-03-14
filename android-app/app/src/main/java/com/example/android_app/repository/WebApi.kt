package com.example.android_app.repository

import com.example.android_app.model.Event
import retrofit2.http.GET

interface WebApi {

    @GET("/events")
    suspend fun getAllEvents(): List<Event>
}