package com.example.shoppinglist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.db.ShoppingListRepository
import com.example.shoppinglist.viewmodels.ShoppingListViewModel

@Suppress("UNCHECKED_CAST")
class ShoppingViewModelFactory(var repo:ShoppingListRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingListViewModel(repo) as T
    }
}