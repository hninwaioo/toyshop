package com.shopping.toyu.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shopping.toyu.model.AddCartToyItemModel

class AddCartToyItemViewModel(var application: Application): ViewModel()
{
    private val db:RoomSingleton = RoomSingleton.getInstance(application)
    internal val allCartToyItem : LiveData<List<AddCartToyItemModel>> = db.addCartToyItemDao().allCartItem()

    fun insert(addCartToyItemModel:AddCartToyItemModel){
        db.addCartToyItemDao().insert(addCartToyItemModel)
    }

    fun delete(id :Int){
        db.addCartToyItemDao().deleteItem(id)
    }
}