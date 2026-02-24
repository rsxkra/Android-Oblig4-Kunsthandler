package com.example.artshop.models

import kotlinx.serialization.Serializable

@Serializable
data class FrameType(
    val id: String,
    val name: String,
    val color: String,
    val extraPrice: Double
)