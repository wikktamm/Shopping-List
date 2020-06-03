package com.example.shoppinglist.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppinglist.models.ShoppingItem

interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item:ShoppingItem)

    @Delete
    suspend fun delete(item:ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    suspend fun getAllItem():LiveData<List<ShoppingItem>>
}