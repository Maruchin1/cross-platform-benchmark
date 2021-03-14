package com.example.android_app.repository

import com.example.android_app.model.Event
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class Repository(
    private val webApi: WebApi,
    private val localDatabaseDao: LocalDatabaseDao
) {
    val isLoadingEvents: StateFlow<Boolean>
        get() = _isLoadingEvents

    val allEventsLoaded: StateFlow<Boolean>
        get() = _allEventsLoaded

    private val _isLoadingEvents = MutableStateFlow(false)
    private val _allEventsLoaded = MutableStateFlow(false)
    private var eventsPageNumber = 0

    fun getAllEvents(): Flow<List<Event>> {
        if (eventsPageNumber == 0) {
            loadNextEventsPage()
        }
        return localDatabaseDao.getAllEvents()
    }

    fun loadNextEventsPage() = GlobalScope.launch {
        _isLoadingEvents.emit(true)
        val loadedEvents = webApi.getEventsPage(eventsPageNumber + 1, 20)
        if (loadedEvents.isNotEmpty()) {
            localDatabaseDao.insertEvents(loadedEvents)
            eventsPageNumber++
        } else {
            _allEventsLoaded.emit(true)
        }
        _isLoadingEvents.emit(false)
    }
}