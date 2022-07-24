package com.shopping.toyu.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shopping.toyu.model.AddCartToyItemModel

@Database(entities = [AddCartToyItemModel::class], version = 1, exportSchema = false)
abstract class AddCartToyItemDatabase : RoomDatabase() {

    abstract val addCartItemDao: AddCartToyItemDao

    companion object {

        @Volatile
        private var INSTANCE: AddCartToyItemDatabase? = null

        fun getInstance(context: Context): AddCartToyItemDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AddCartToyItemDatabase::class.java,
                        "user_details_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}