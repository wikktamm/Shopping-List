package com.example.shoppinglist.viewmodels

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.db.ShoppingListRepository
import com.example.shoppinglist.models.ShoppingItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListViewModel(private val repo:ShoppingListRepository) : ViewModel() {
    fun upsert(item:ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repo.upsert(item)
    }
    fun delete(item:ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repo.delete(item)
    }
    fun getAllItems() = repo.getAllItems()
}