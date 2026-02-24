package com.example.artshop.models

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val id: String,
    val firstName: String,
    val lastName: String
)
