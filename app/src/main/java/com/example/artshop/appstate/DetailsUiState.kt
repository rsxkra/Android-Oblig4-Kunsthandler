package com.example.artshop.appstate

import com.example.artshop.models.FrameType
import com.example.artshop.models.FrameWidth
import com.example.artshop.models.Photo
import com.example.artshop.models.PhotoSize

data class DetailsUiState(
    val photo: Photo? = null,
    val frameTypes: List<FrameType> = emptyList(),
    val frameWidths: List<FrameWidth> = emptyList(),
    val photoSizes: List<PhotoSize> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedFrameType: FrameType? = null,
    val selectedFrameWidth: FrameWidth? = null,
    val selectedPhotoSize: PhotoSize? = null,
    val showFrameTypeDropdown: Boolean = false,
    val showFrameWidthDropdown: Boolean = false,
    val showPhotoSizeDropdown: Boolean = false
)
