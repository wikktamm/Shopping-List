package com.example.shoppinglist.db

import com.example.shoppinglist.models.ShoppingItem

class ShoppingListRepository(private val db: ShoppingDatabase) {

    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)
    fun getAllItems() = db.getShoppingDao().getAllItems()
}