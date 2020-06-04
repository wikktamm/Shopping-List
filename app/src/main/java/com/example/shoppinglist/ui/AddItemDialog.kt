package com.example.shoppinglist.ui

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.models.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_item.*

class AddItemDialog(context: Context, val listener: AddDialogListener) : AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bt_accept.setOnClickListener {
            if (et_name.text.isEmpty() || et_count.text.isEmpty()) {
                Toast.makeText(context, "You need to enter the required data!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val nameInput = et_name.text.toString()
            val countInput = et_count.text.toString().toInt()
            val itemToInsert = ShoppingItem(nameInput, countInput)
            listener.onAcceptListener(itemToInsert)
        }
        bt_cancel.setOnClickListener { cancel() }
    }
}

