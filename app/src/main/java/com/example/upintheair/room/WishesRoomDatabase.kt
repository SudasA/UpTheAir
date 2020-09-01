package com.example.upintheair.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.upintheair.dao.WishesDAO
import com.example.upintheair.entity.Wish

@Database(version = 1, entities = arrayOf(Wish::class))
abstract class WishesRoomDatabase : RoomDatabase() {
    abstract fun getWishesDao(): WishesDAO

    companion object {
        @Volatile
        private var INSTANCE: WishesRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                WishesRoomDatabase::class.java,
                "wishes"
            ).build()
    }
}