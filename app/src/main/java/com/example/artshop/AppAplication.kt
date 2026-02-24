package com.example.artshop

import android.app.Application
import androidx.room.Room
import com.example.artshop.data.BasketDatabase
import com.example.artshop.di.AppContainer
import com.example.artshop.di.DefaultAppContainer

class AppApplication : Application() {
    lateinit var container: AppContainer
    lateinit var basketDatabase: BasketDatabase // Add this line
    override fun onCreate() {
        super.onCreate()
        basketDatabase = Room.databaseBuilder( // Add this block
            applicationContext,
            BasketDatabase::class.java,
            "basket_database"
        ).build()
        container = DefaultAppContainer(this)
    }
}