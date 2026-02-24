package com.example.artshop.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Basket(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val photoId: String,
    val frameType: String,
    val frameWidth: String,
    val photoSize: String,
    val photoPrice: Double

)
