package com.example.webapi.controllers

import com.example.webapi.model.Event
import com.example.webapi.service.EventService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController(
    private val eventService: EventService
) {

    @CrossOrigin(origins = ["http://localhost:4200"])
    @GetMapping("/events/page")
    fun getPage(@RequestParam pageNumber: Int, @RequestParam pageSize: Int): List<Event> {
        return eventService.getPage(pageNumber, pageSize)
    }

}