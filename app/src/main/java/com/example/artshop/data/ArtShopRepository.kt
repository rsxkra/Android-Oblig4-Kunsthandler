package com.example.artshop.data

import com.example.artshop.models.Artist
import com.example.artshop.models.Category
import com.example.artshop.models.FrameType
import com.example.artshop.models.FrameWidth
import com.example.artshop.models.Photo
import com.example.artshop.models.PhotoSize
import com.example.artshop.network.ApiService
import com.example.artshop.network.RetrofitInstance

class ArtShopRepository() {
    suspend fun getCategories(): List<Category> = RetrofitInstance.api.getCategories()
    suspend fun getArtists(): List<Artist> = RetrofitInstance.api.getArtists()
    suspend fun getPhotos(): List<Photo> = RetrofitInstance.api.getPhotos()
    suspend fun getPhotoById(photoId: String): Photo {
        return RetrofitInstance.api.getPhotoById(photoId)
    }
    suspend fun getFrameTypes(): List<FrameType> = RetrofitInstance.api.getFrameTypes()
    suspend fun getFrameWidths(): List<FrameWidth> = RetrofitInstance.api.getFrameWidths()
    suspend fun getPhotoSizes(): List<PhotoSize> = RetrofitInstance.api.getPhotoSizes()

}