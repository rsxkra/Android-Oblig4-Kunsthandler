package com.example.artshop.di

import com.example.artshop.AppApplication
import com.example.artshop.data.BasketDatabase
import com.example.artshop.data.ArtShopRepository
import com.example.artshop.data.BasketRepository
import com.example.artshop.data.DefaultBasketRepository

class DefaultAppContainer(private val application: AppApplication) : AppContainer {
    override val artShopRepository: ArtShopRepository by lazy {
        ArtShopRepository()
    }

    private val database by lazy { BasketDatabase.getDatabase(application.applicationContext) }

    override val basketRepository: BasketRepository by lazy {
        DefaultBasketRepository(database.basketDao())
    }
}