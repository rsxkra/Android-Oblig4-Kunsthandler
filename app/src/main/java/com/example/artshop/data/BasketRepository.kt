package com.example.artshop.data

import com.example.artshop.data.entity.Basket
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    suspend fun upsertPhoto(basket: Basket)
    suspend fun deletePhoto(basket: Basket)
    fun getAllPhotos(): Flow<List<Basket>>
    suspend fun clearBasket()
}

class DefaultBasketRepository(private val basketDao: BasketDao) : BasketRepository {
    override suspend fun upsertPhoto(basket: Basket) = basketDao.upsertPhoto(basket)
    override suspend fun deletePhoto(basket: Basket) = basketDao.deletePhoto(basket)
    override fun getAllPhotos(): Flow<List<Basket>> = basketDao.getAllPhotos()
    override suspend fun clearBasket() = basketDao.clearBasket()
}