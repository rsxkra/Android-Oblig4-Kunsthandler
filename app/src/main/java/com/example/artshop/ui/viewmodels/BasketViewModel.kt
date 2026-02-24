package com.example.artshop.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artshop.appstate.BasketEvent
import com.example.artshop.appstate.BasketUiState
import com.example.artshop.di.AppContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BasketViewModel (private val appContainer: AppContainer) : ViewModel() {
    private val _state = MutableStateFlow(BasketUiState())
    val state: StateFlow<BasketUiState> = _state.asStateFlow()


    /*
    val basket = dao.getAllPhotos().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(), emptyList()
    )
     */

    init {
        viewModelScope.launch {
                appContainer.basketRepository.getAllPhotos().collect { basket->
                _state.update { currentState ->
                    currentState.copy(
                        basket = basket,
                        totalPics = basket.size,
                        totalPrice = basket.sumOf { it.photoPrice })
                }
            }
        }
    }

    fun onEvent(event: BasketEvent) {
        when (event) {
            is BasketEvent.DeletePhoto -> {
                viewModelScope.launch {
                    appContainer.basketRepository.deletePhoto(event.photo)
                }
            }

            is BasketEvent.SavePhoto -> {
                viewModelScope.launch {
                    appContainer.basketRepository.upsertPhoto(event.photo)
                }
            }

            is BasketEvent.ClearBasket -> {
                viewModelScope.launch {
                    appContainer.basketRepository.clearBasket()
                }
            }
        }
    }
}