package com.shopping.toyu.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shopping.toyu.model.AddCartToyItemModel

@Dao
interface AddCartToyItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cartitem: AddCartToyItemModel?)

    @Query("SELECT * FROM cart_item ORDER BY id DESC")
    fun allCartItem(): LiveData<List<AddCartToyItemModel>>

    @Query("DELETE FROM cart_item WHERE id = :id" )
    fun deleteItem(id : Int?)

}