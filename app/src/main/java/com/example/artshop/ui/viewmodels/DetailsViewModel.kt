package com.example.artshop.ui.viewmodels

import android.util.Log
import androidx.compose.animation.core.copy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artshop.appstate.DetailsEvent
import com.example.artshop.appstate.DetailsUiState
import com.example.artshop.di.AppContainer
import com.example.artshop.models.FrameType
import com.example.artshop.models.FrameWidth
import com.example.artshop.models.Photo
import com.example.artshop.models.PhotoSize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(private val appContainer: AppContainer) : ViewModel() {
    private val repository = appContainer.artShopRepository

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.LoadPhotoDetails -> loadPhotoDetails(event.photoId)
            is DetailsEvent.UpdateSelectedFrameType -> updateSelectedFrameType(event.frameType)
            is DetailsEvent.UpdateSelectedFrameWidth -> updateSelectedFrameWidth(event.frameWidth)
            is DetailsEvent.UpdateSelectedPhotoSize -> updateSelectedPhotoSize(event.photoSize)
            DetailsEvent.ToggleFrameTypeDropdown -> toggleFrameTypeDropdown()
            DetailsEvent.ToggleFrameWidthDropdown -> toggleFrameWidthDropdown()
            DetailsEvent.TogglePhotoSizeDropdown -> togglePhotoSizeDropdown()
        }
    }

    private fun loadPhotoDetails(photoId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val photo = repository.getPhotoById(photoId)
                val frameTypes = repository.getFrameTypes()
                val frameWidths = repository.getFrameWidths()
                val photoSizes = repository.getPhotoSizes()
                _uiState.update {
                    it.copy(
                        photo = photo,
                        frameTypes = frameTypes,
                        frameWidths = frameWidths,
                        photoSizes = photoSizes,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = "Failed to load photo details: ${e.message}",
                        isLoading = false
                    )
                }
                Log.e("DetailsViewModel", "Error loading photo details", e)
            }
        }
    }

    private fun updateSelectedFrameType(frameType: FrameType?) {
        _uiState.update { it.copy(selectedFrameType = frameType) }
    }

    private fun updateSelectedFrameWidth(frameWidth: FrameWidth?) {
        _uiState.update { it.copy(selectedFrameWidth = frameWidth) }
    }

    private fun updateSelectedPhotoSize(photoSize: PhotoSize?) {
        _uiState.update { it.copy(selectedPhotoSize = photoSize) }
    }

    private fun toggleFrameTypeDropdown() {
        _uiState.update { it.copy(showFrameTypeDropdown = !it.showFrameTypeDropdown) }
    }

    private fun toggleFrameWidthDropdown() {
        _uiState.update { it.copy(showFrameWidthDropdown = !it.showFrameWidthDropdown) }
    }

    private fun togglePhotoSizeDropdown() {
        _uiState.update { it.copy(showPhotoSizeDropdown = !it.showPhotoSizeDropdown) }
    }


/*
    // Photo details
    private val _photo = MutableStateFlow<Photo?>(null)
    val photo: StateFlow<Photo?> = _photo.asStateFlow()

    private val _frameTypes = MutableStateFlow<List<FrameType?>>(emptyList())
    val frameTypes: StateFlow<List<FrameType?>> = _frameTypes.asStateFlow()

    private val _frameWidths = MutableStateFlow<List<FrameWidth?>>(emptyList())
    val frameWidths: StateFlow<List<FrameWidth?>> = _frameWidths.asStateFlow()

    private val _photoSizes = MutableStateFlow<List<PhotoSize?>>(emptyList())
    val photoSizes: StateFlow<List<PhotoSize?>> = _photoSizes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Frame and Photo Selection
    private val _selectedFrameType = MutableStateFlow<FrameType?>(null)
    val selectedFrameType: StateFlow<FrameType?> = _selectedFrameType.asStateFlow()

    private val _selectedFrameWidth = MutableStateFlow<FrameWidth?>(null)
    val selectedFrameWidth: StateFlow<FrameWidth?> = _selectedFrameWidth.asStateFlow()

    private val _selectedPhotoSize = MutableStateFlow<PhotoSize?>(null)
    val selectedPhotoSize: StateFlow<PhotoSize?> = _selectedPhotoSize.asStateFlow()

    // Dropdown Visibility
    private val _showFrameTypeDropdown = MutableStateFlow(false)
    val showFrameTypeDropdown: StateFlow<Boolean> = _showFrameTypeDropdown.asStateFlow()

    private val _showFrameWidthDropdown = MutableStateFlow(false)
    val showFrameWidthDropdown: StateFlow<Boolean> = _showFrameWidthDropdown.asStateFlow()

    private val _showPhotoSizeDropdown = MutableStateFlow(false)
    val showPhotoSizeDropdown: StateFlow<Boolean> = _showPhotoSizeDropdown.asStateFlow()

    // Load Photo Details
    fun loadPhotoDetails(photoId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _photo.value = repository.getPhotoById(photoId)
                _frameTypes.value = repository.getFrameTypes()
                _frameWidths.value = repository.getFrameWidths()
                _photoSizes.value = repository.getPhotoSizes()
            } catch (e: Exception) {
                _error.value = "Failed to load photo details: ${e.message}"
                Log.e("DetailsViewModel", "Error loading photo details", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Functions to Update State
    fun updateSelectedFrameType(frameType: FrameType?) {
        _selectedFrameType.value = frameType
    }

    fun updateSelectedFrameWidth(frameWidth: FrameWidth?) {
        _selectedFrameWidth.value = frameWidth
    }

    fun updateSelectedPhotoSize(photoSize: PhotoSize?) {
        _selectedPhotoSize.value = photoSize
    }

    fun toggleFrameTypeDropdown() {
        _showFrameTypeDropdown.value = !_showFrameTypeDropdown.value
    }

    fun toggleFrameWidthDropdown() {
        _showFrameWidthDropdown.value = !_showFrameWidthDropdown.value
    }

    fun togglePhotoSizeDropdown() {
        _showPhotoSizeDropdown.value = !_showPhotoSizeDropdown.value
    }

 */
}