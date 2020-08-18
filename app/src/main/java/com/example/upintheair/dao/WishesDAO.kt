package com.example.upintheair.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.upintheair.entity.Wish

@Dao
interface WishesDAO {
    @Query("SELECT * from wishes")
    fun getAllWishes(): MutableList<Wish>

    @Query("SELECT * from wishes WHERE wish_id=:id")
    fun getWish(id: Int): Wish

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createWish(wish: Wish)

    @Query("DELETE FROM wishes")
    suspend fun deleteAllWishes()

    @Query("DELETE FROM wishes WHERE wish_id = :id")
    suspend fun deleteWishById(id: Int)
}