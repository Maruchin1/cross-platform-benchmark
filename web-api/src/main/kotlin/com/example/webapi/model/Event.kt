package com.example.webapi.model

data class Event(
    val id: Long,
    val imageUrl: String,
    val name: String,
    val date: String,
    val place: String,
    val description: String,
    val galleryImagesUrls: List<String>
)