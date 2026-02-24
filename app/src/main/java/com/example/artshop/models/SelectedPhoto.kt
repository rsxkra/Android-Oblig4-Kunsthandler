package com.example.artshop.models


data class SelectedPhoto(
    val photoId: String,
    val frameType: FrameType,
    val frameWidth: FrameWidth,
    val photoSize: PhotoSize,
    val photoPrice: Double
)