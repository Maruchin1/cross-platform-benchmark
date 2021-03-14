package com.example.android_app.repository

import com.example.android_app.model.Event
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class Repository(
    private val webApi: WebApi,
    private val localDatabaseDao: LocalDatabaseDao
) {
    private var eventsRefreshRequired = true

    fun getAllEvents(): Flow<List<Event>> {
        if (eventsRefreshRequired) {
            refreshEvents()
            eventsRefreshRequired = false
        }
        return localDatabaseDao.getAllEvents()
    }

    private fun refreshEvents() = GlobalScope.launch {
        val loadedEvents = webApi.getAllEvents()
        localDatabaseDao.insertEvents(loadedEvents)
    }
}