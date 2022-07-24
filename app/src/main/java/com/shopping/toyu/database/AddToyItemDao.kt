package com.shopping.toyu.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shopping.toyu.model.ToyDataListItem

@Dao
interface AddToyItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToyItems(toyitem: ToyDataListItem?)

    @Query("SELECT * FROM toy_item ORDER BY id DESC")
    fun allCartItem(): LiveData<List<ToyDataListItem>>

    @Query("SELECT * FROM toy_item")
    fun getAllToyItems(): List<ToyDataListItem>

//    @Insert
//    fun insertAllCartItem(users: List<AddCartItemModel>?)
//
//    @Update
//    fun update(note: AddCartItemModel?)
//
//    @Delete
//    fun delete(note: AddCartItemModel?)
//
//    @Query("DELETE FROM cart_item")
//    fun deleteAllCartItem()
//
//    @Query("SELECT * FROM cart_item ORDER BY priority DESC")
//    fun getAllCartItem(): LiveData<List<AddCartItemModel>>?
}