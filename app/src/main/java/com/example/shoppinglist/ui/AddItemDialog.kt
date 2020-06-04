package com.example.shoppinglist.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.R
import com.example.shoppinglist.models.ShoppingItem
import com.example.shoppinglist.other.getIntContent
import com.example.shoppinglist.other.getStringContent
import kotlinx.android.synthetic.main.dialog_add_item.*

class AddItemDialog(context: Context, val listener: AddDialogListener) : AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_item)
        bt_accept.setOnClickListener {
            if (et_name.text.isEmpty() || et_count.text.isEmpty()) {
                Toast.makeText(context, "You need to enter the required data!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val nameInput = et_name.getStringContent()
            val countInput = et_count.getIntContent()
            val itemToInsert = ShoppingItem(nameInput, countInput)
            listener.onAcceptListener(itemToInsert)
            dismiss()
        }
        bt_cancel.setOnClickListener { cancel() }
    }
}

