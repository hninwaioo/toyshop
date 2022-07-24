package com.shopping.toyu.model

import android.app.appsearch.StorageInfo
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class AddCartToyItemModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    @ColumnInfo(name = "image")
    var image: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "toy_release")
    var toy_release: String,

    @ColumnInfo(name = "totalprice")
    var totalprice: Float,

    @ColumnInfo(name = "itemcount")
    var itemcount: Int,

    @ColumnInfo(name = "owner_name")
    var owner_name : String,

    @ColumnInfo(name = "owner_image")
    var owner_image : Int

)