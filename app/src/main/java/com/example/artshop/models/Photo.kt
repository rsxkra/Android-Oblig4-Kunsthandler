package com.example.artshop.models

import kotlinx.serialization.Serializable



@Serializable
data class Photo(
    val id: String,
    val title: String,
    val imageThumbUrl: String,
    val imageUrl: String,
    val artistId: String,
    val categoryId: String,
    val price: Double
)