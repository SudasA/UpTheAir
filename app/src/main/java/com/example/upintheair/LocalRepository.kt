package com.example.upintheair

import android.content.Context
import com.example.upintheair.dao.WishesDAO
import com.example.upintheair.entity.Wish

class LocalRepository(context: Context) {
    private val wishesDAO: WishesDAO = WishesRoomDatabase(context).getWishesDao()

    suspend fun getAllWishes() = wishesDAO.getAllWishes()

    suspend fun createWish(wish: Wish) {
        wishesDAO.createWish(wish)
    }
}