package com.example.android_app

import com.example.android_app.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object Repository {

    private val events = listOf(
        Event(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
            name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
            date = "2021.01.21"
        ),
        Event(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
            name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
            date = "2021.01.21"
        ),
        Event(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
            name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
            date = "2021.01.21"
        ),
        Event(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
            name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
            date = "2021.01.21"
        ),
        Event(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
            name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
            date = "2021.01.21"
        ),
        Event(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
            name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
            date = "2021.01.21"
        ),
        Event(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
            name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
            date = "2021.01.21"
        ),
        Event(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
            name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
            date = "2021.01.21"
        ),
        Event(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
            name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
            date = "2021.01.21"
        ),
        Event(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3a/Akademia_Sztuk_Pięknych_pl._Polski_3-4_Fot_BMaliszewska.JPG",
            name = "Nazwa wydarzenia, które może mieć więcej niż jedną linijkę",
            date = "2021.01.21"
        )
    )

    fun getAllEvents(): Flow<List<Event>> = flowOf(events)
}