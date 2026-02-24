package com.example.artshop.di

import com.example.artshop.data.ArtShopRepository
import com.example.artshop.data.BasketRepository

interface AppContainer {
    val artShopRepository: ArtShopRepository
    val basketRepository: BasketRepository

}