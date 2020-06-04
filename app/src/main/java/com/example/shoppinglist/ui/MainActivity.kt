package com.example.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.db.ShoppingDatabase
import com.example.shoppinglist.db.ShoppingListRepository
import com.example.shoppinglist.other.ShoppingItemAdapter
import com.example.shoppinglist.viewmodels.ShoppingListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = ShoppingDatabase(this)
        val repo = ShoppingListRepository(db)
        val factory = ShoppingViewModelFactory(repo)
        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingListViewModel::class.java)
        setRecyclerViewAndFetchData(viewModel, repo)
    }

    private fun setRecyclerViewAndFetchData(vm: ShoppingListViewModel, repo: ShoppingListRepository) {
        val adapter = ShoppingItemAdapter(listOf(), vm)
        rv_items.layoutManager = LinearLayoutManager(this)
        rv_items.adapter = adapter
        repo.getAllItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
    }
}
