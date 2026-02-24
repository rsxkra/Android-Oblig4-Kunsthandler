package com.example.artshop.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.artshop.data.entity.Basket
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Upsert
    suspend fun upsertPhoto(basket: Basket)

    @Delete
    suspend fun deletePhoto(basket: Basket)

    @Query("SELECT * FROM basket")
    fun getAllPhotos(): Flow<List<Basket>>

    @Query("DELETE FROM basket")
    suspend fun clearBasket()

}