package com.example.artshop.models

import kotlinx.serialization.Serializable

@Serializable
data class PhotoSize(
    val id: String,
    val name: String,
    val size: Int,
    val extraPrice: Double

)