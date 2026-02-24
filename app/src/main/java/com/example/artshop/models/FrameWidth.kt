package com.example.artshop.models

import kotlinx.serialization.Serializable


@Serializable
data class FrameWidth(
    val id: String,
    val name: String,
    val width: Int,
    val extraPrice: Double
)