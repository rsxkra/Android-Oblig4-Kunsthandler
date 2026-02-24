package com.example.artshop
/*
import com.example.artshop.models.Basket
import com.example.artshop.models.FrameType
import com.example.artshop.models.FrameWidth
import com.example.artshop.models.PhotoSize
import com.example.artshop.data.createSelectedPhoto
import com.example.artshop.data.photos
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class PriceCheckerUnitTest {
    @Before
    fun setup() {
        Basket.clearBasket()
    }

    @Test
    fun price_checker_one_photo() {
        val photo = photos.find { it.id.toInt() == 2 }

        if (photo != null) {
            val selectedPhoto = createSelectedPhoto(photo, FrameType.WOOD, FrameWidth.WIDTH_15, PhotoSize.LARGE)
            Basket.addPhoto(selectedPhoto)
        }

        val totalPrice = Basket.calculateTotalPrice()

        assertEquals(530.0, totalPrice, 0.001)
    }

    @Test
    fun price_checker_multiple_photos() {
        val photo1 = photos.find { it.id.toInt() == 4 }
        val photo2 = photos.find { it.id.toInt() == 6 }

        if (photo1 != null && photo2 != null) {
            val selectedPhoto1 = createSelectedPhoto(photo1, FrameType.WOOD, FrameWidth.WIDTH_10, PhotoSize.LARGE)
            val selectedPhoto2 = createSelectedPhoto(photo2, FrameType.METAL, FrameWidth.WIDTH_20, PhotoSize.MEDIUM)
            Basket.addPhoto(selectedPhoto1)
            Basket.addPhoto(selectedPhoto2)
        }

        val totalPrice = Basket.calculateTotalPrice()

        assertEquals(1160.0, totalPrice, 0.001)
    }

    @Test
    fun price_checker_remove_photo() {
        val photo1 = photos.find { it.id.toInt() == 4 }
        val photo2 = photos.find { it.id.toInt() == 6 }

        if (photo1 != null && photo2 != null) {
            val selectedPhoto1 = createSelectedPhoto(photo1, FrameType.WOOD, FrameWidth.WIDTH_10, PhotoSize.LARGE)
            val selectedPhoto2 = createSelectedPhoto(photo2, FrameType.METAL, FrameWidth.WIDTH_20, PhotoSize.MEDIUM)
            Basket.addPhoto(selectedPhoto1)
            Basket.addPhoto(selectedPhoto2)
            Basket.removePhoto(selectedPhoto1)
        }

        val totalPrice = Basket.calculateTotalPrice()

        assertEquals(730.0, totalPrice, 0.001)
    }
}

 */