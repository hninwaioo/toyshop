package com.shopping.toyu.viewmodel
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.shopping.toyu.database.AddCartToyItemDao
import com.shopping.toyu.model.AddCartToyItemModel

@Database(entities = arrayOf(AddCartToyItemModel::class), version = 1, exportSchema = false)
abstract class RoomSingleton : RoomDatabase(){
    abstract fun addCartToyItemDao(): AddCartToyItemDao

    companion object{
        private var INSTANCE: RoomSingleton? = null
        fun getInstance(context:Context): RoomSingleton{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    RoomSingleton::class.java,
                    "roomdb")
                    .build()
            }

            return INSTANCE as RoomSingleton
        }
    }
}