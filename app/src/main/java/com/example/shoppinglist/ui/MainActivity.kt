package com.example.shoppinglist.ui

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.db.ShoppingDatabase
import com.example.shoppinglist.db.ShoppingListRepository
import com.example.shoppinglist.models.ShoppingItem
import com.example.shoppinglist.other.ShoppingItemAdapter
import com.example.shoppinglist.viewmodels.ShoppingListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ShoppingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = ShoppingDatabase(this)
        val repo = ShoppingListRepository(db)
        val factory = ShoppingViewModelFactory(repo)
        viewModel = ViewModelProviders.of(this, factory).get(ShoppingListViewModel::class.java)
        setRecyclerViewAndLiveData(viewModel, repo)
        setListeners()
        title = getString(R.string.my_shopping_list).toUpperCase()
    }

    private fun setRecyclerViewAndLiveData(
        vm: ShoppingListViewModel,
        repo: ShoppingListRepository
    ) {
        val adapter = ShoppingItemAdapter(listOf(), vm)
        rv_items.layoutManager = LinearLayoutManager(this)
        rv_items.adapter = adapter
        repo.getAllItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
    }

    private fun setListeners() {
        fab_add.setOnClickListener {
            val dialog = AddItemDialog(this, object : AddDialogListener {
                override fun onAcceptListener(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            })
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setTitle(getString(R.string.add_new_title).toUpperCase())
            dialog.show()
        }
    }
}
