package com.example.shoppinglist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.models.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun getShoppingDao(): ShoppingDao

    companion object {
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) {
            instance ?: synchronized(LOCK) {
                createDatabase(context).also { instance = it }
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context, ShoppingDatabase::class.java, "ShoppingDatabase.db").build()
    }
}