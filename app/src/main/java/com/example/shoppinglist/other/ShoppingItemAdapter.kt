package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.models.ShoppingItem
import com.example.shoppinglist.viewmodels.ShoppingListViewModel
import kotlinx.android.synthetic.main.row_shopping_item.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingListViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_shopping_item, parent, false)
        return ShoppingItemVH(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingItemVH, position: Int) {
        val item = items[position]
        val view = holder.view
        view.tv_name.text = item.name
        view.tv_count.text = item.count.toString()
        view.bt_plus.setOnClickListener {
            item.count++
            viewModel.upsert(item)
        }
        view.bt_minus.setOnClickListener {
            if (item.count > 1) {
                item.count--
                viewModel.upsert(item)
            } else {
                viewModel.delete(item)
            }
        }
        view.bt_remove.setOnClickListener {
            viewModel.delete(item)
        }
    }

    class ShoppingItemVH(var view: View) : RecyclerView.ViewHolder(view)
}