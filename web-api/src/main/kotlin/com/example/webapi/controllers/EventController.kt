package com.example.webapi.controllers

import com.example.webapi.model.Event
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController {

    @GetMapping("/events")
    fun getAll(): List<Event> {
        return listOf(
            Event(
                id = 1,
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
                name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
                date = "2021.01.21"
            ),
            Event(
                id = 2,
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
                name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
                date = "2021.01.21"
            ),
            Event(
                id = 3,
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
                name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
                date = "2021.01.21"
            ),
            Event(
                id = 4,
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
                name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
                date = "2021.01.21"
            ),
            Event(
                id = 5,
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
                name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
                date = "2021.01.21"
            ),
            Event(
                id = 6,
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
                name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
                date = "2021.01.21"
            ),
            Event(
                id = 7,
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
                name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
                date = "2021.01.21"
            ),
            Event(
                id = 8,
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
                name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
                date = "2021.01.21"
            ),
            Event(
                id = 9,
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
                name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
                date = "2021.01.21"
            ),
            Event(
                id = 10,
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
                name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
                date = "2021.01.21"
            )
        )
    }
}