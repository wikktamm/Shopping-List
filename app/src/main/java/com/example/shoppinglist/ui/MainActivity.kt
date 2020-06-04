package com.example.shoppinglist.ui

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.models.ShoppingItem
import com.example.shoppinglist.other.ShoppingItemAdapter
import com.example.shoppinglist.viewmodels.ShoppingListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()

    private val factory : ShoppingViewModelFactory by instance<ShoppingViewModelFactory>()
    private lateinit var viewModel: ShoppingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, factory).get(ShoppingListViewModel::class.java)
        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        rv_items.layoutManager = LinearLayoutManager(this)
        rv_items.adapter = adapter
        viewModel.getAllItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
        setListeners()
        title = getString(R.string.my_shopping_list).toUpperCase()
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
