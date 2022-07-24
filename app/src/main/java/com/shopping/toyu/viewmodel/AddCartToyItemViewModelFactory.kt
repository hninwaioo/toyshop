package com.shopping.toyu.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddCartToyItemViewModelFactory(var application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddCartToyItemViewModel::class.java)) {
            return AddCartToyItemViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}