package com.example.artshop.appstate

import com.example.artshop.models.FrameType
import com.example.artshop.models.FrameWidth
import com.example.artshop.models.PhotoSize

sealed interface DetailsEvent {
    data class LoadPhotoDetails(val photoId: String) : DetailsEvent
    data class UpdateSelectedFrameType(val frameType: FrameType?) : DetailsEvent
    data class UpdateSelectedFrameWidth(val frameWidth: FrameWidth?) : DetailsEvent
    data class UpdateSelectedPhotoSize(val photoSize: PhotoSize?) : DetailsEvent
    data object ToggleFrameTypeDropdown : DetailsEvent
    data object ToggleFrameWidthDropdown : DetailsEvent
    data object TogglePhotoSizeDropdown : DetailsEvent
}