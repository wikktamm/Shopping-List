package com.example.shoppinglist.other

import android.widget.EditText

fun EditText.getIntContent(): Int {
    return this.text.toString().toInt()
}

fun EditText.getStringContent(): String {
    return this.text.toString()
}