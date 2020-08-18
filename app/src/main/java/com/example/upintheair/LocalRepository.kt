package com.example.upintheair

import android.content.Context
import com.example.upintheair.dao.WishesDAO
import com.example.upintheair.entity.Wish

class LocalRepository(context: Context) {
    private val wishesDAO: WishesDAO = WishesRoomDatabase(context).getWishesDao()

    fun getAllWishes() = wishesDAO.getAllWishes()

    suspend fun createWish(wish: Wish) = wishesDAO.createWish(wish)

    fun getWish(id: Int) = wishesDAO.getWish(id)

    suspend fun deleteWishById(id: Int) = wishesDAO.deleteWishById(id)
}