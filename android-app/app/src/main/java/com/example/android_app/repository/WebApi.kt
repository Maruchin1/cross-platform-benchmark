package com.example.android_app.repository

import com.example.android_app.model.Event
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApi {

    @GET("/events/page")
    suspend fun getEventsPage(
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): List<Event>
}