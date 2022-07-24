package com.shopping.toyu.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shopping.toyu.model.ToyDataListItem

@Database(entities = [ToyDataListItem::class], version = 1, exportSchema = false)
abstract class AddToyItemDatabase: RoomDatabase() {
    abstract fun getAllToyItemDao(): AddToyItemDao

    companion object {
        @Volatile
        var instance: AddToyItemDatabase? = null
        fun getInstance(context: Context): AddToyItemDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context,AddToyItemDatabase::class.java,"the_database.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as AddToyItemDatabase
        }
    }
}