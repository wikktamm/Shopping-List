package com.example.shoppinglist.ui

import com.example.shoppinglist.models.ShoppingItem

interface AddDialogListener {
    fun onAcceptListener(item:ShoppingItem)
}