package com.example.webapi.service

import com.example.webapi.model.Event
import com.thedeanda.lorem.LoremIpsum
import org.springframework.stereotype.Service

@Service
class EventService {
    companion object {
        const val NUM_OF_EVENTS = 1_000
        const val NAME_WORDS = 10
        const val PLACE_WORDS = 3
        const val DESCRIPTION_PARAGRAPHS = 4
        const val GALLERY_SIZE = 5
        const val IMAGE_LONGER_EDGE = 800
        const val IMAGE_SHORTER_EDGE = 600
    }

    private val loremIpsum = LoremIpsum.getInstance()

    private val eventsImages = generateImages(
        numOfImages = NUM_OF_EVENTS,
        imageWidth = IMAGE_LONGER_EDGE,
        imageHeight = IMAGE_SHORTER_EDGE,
    )

    private val galleryImages = generateImages(
        numOfImages = NUM_OF_EVENTS * GALLERY_SIZE,
        imageWidth = IMAGE_SHORTER_EDGE,
        imageHeight = IMAGE_LONGER_EDGE,
    )

    private val events = generateEvents()

    fun getPage(pageNumber: Int, pageSize: Int): List<Event> {
        return events.chunked(pageSize)[pageNumber - 1]
    }

    private fun generateEvents(): List<Event> {
        return (1..NUM_OF_EVENTS).map { eventId ->
            Event(
                id = eventId.toLong(),
                imageUrl = eventsImages.removeLast(),
                name = loremIpsum.getWords(NAME_WORDS),
                date = "2021.01.21",
                place = loremIpsum.getWords(PLACE_WORDS),
                description = loremIpsum.getParagraphs(DESCRIPTION_PARAGRAPHS, DESCRIPTION_PARAGRAPHS),
                galleryImagesUrls = (1..GALLERY_SIZE).map { galleryImages.removeLast() }
            )
        }
    }

    private fun generateImages(
        numOfImages: Int,
        imageWidth: Int,
        imageHeight: Int
    ): MutableList<String> {
        return (1..numOfImages).map {
            val id = (1..1_080).random()
            "https://picsum.photos/id/$id/$imageWidth/$imageHeight"
        }.toMutableList()
    }
}