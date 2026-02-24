package com.example.artshop.appstate

import com.example.artshop.data.entity.Basket

sealed interface BasketEvent {
    data class SavePhoto(val photo: Basket) : BasketEvent
    data class DeletePhoto(val photo: Basket) : BasketEvent
    data object ClearBasket : BasketEvent

}