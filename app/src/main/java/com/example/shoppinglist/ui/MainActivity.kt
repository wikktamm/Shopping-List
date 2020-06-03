package com.example.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.shoppinglist.R
import com.example.shoppinglist.db.ShoppingDatabase
import com.example.shoppinglist.db.ShoppingListRepository
import com.example.shoppinglist.viewmodels.ShoppingListViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = ShoppingDatabase(this)
        val repo = ShoppingListRepository(db)
        val factory = ShoppingViewModelFactory(repo)
        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingListViewModel::class.java)
    }
}
