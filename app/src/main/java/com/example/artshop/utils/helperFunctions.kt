package com.example.artshop.utils

import android.util.Log
import com.example.artshop.models.FrameType
import com.example.artshop.models.FrameWidth
import com.example.artshop.models.Photo
import com.example.artshop.models.PhotoSize
import com.example.artshop.models.SelectedPhoto

fun calculatePrice(photo: Photo?, frameType: FrameType?, frameWidth: FrameWidth?, photoSize: PhotoSize?): Double {
    Log.d("calculatePrice", "Photo: $photo")
    Log.d("calculatePrice", "Frame Type: $frameType")
    Log.d("calculatePrice", "Frame Width: $frameWidth")
    Log.d("calculatePrice", "Photo Size: $photoSize")
    Log.d("calculatePrice", "Photo Price: ${photo?.price ?: 0.0}")


    return if (photo != null && frameType != null && frameWidth != null && photoSize != null) {
        photo.price + frameType.extraPrice + frameWidth.extraPrice + photoSize.extraPrice
    } else {
        0.0

    }

}

fun createSelectedPhoto(photo: Photo?, frameType: FrameType?, frameWidth: FrameWidth?, photoSize: PhotoSize?): SelectedPhoto {
    val price = calculatePrice(photo, frameType, frameWidth, photoSize)
    return if (photo != null && frameType != null && frameWidth != null && photoSize != null) {
        SelectedPhoto(photo.id, frameType, frameWidth, photoSize, price)
    } else {
        SelectedPhoto("", FrameType("", "", "", 0.0), FrameWidth("", "", 0, 0.0), PhotoSize("", "", 0, 0.0), 0.0)


    }
}