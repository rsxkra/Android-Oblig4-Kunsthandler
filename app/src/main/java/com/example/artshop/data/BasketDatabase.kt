package com.example.artshop.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.artshop.data.entity.Basket

@Database(
    entities = [Basket::class],
    version = 1,
    exportSchema = false
)
abstract class BasketDatabase: RoomDatabase()  {
    abstract fun basketDao(): BasketDao

    companion object {
        @Volatile
        private var INSTANCE: BasketDatabase? = null

        fun getDatabase(context: Context): BasketDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BasketDatabase::class.java,
                    "basket_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}