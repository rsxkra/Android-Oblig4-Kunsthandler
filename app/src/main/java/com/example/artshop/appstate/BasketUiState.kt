package com.example.artshop.appstate

import com.example.artshop.data.entity.Basket


data class BasketUiState(
    val basket: List<Basket> = emptyList(),
    val totalPics: Int = 0,
    val totalPrice: Double = 0.0,
)