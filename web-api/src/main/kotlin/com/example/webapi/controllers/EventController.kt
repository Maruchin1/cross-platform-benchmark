package com.example.webapi.controllers

import com.example.webapi.model.Event
import com.example.webapi.service.EventService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController(
    private val eventService: EventService
) {

    @GetMapping("/events")
    fun getAll(): List<Event> {
        return eventService.getAll()
    }

}