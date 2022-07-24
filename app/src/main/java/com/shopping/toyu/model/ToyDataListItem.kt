package com.shopping.toyu.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "toy_item")
data class ToyDataListItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "release")
    var release: String,

    @ColumnInfo(name = "price")
    var price: Float,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "image")
    var image: Int,

    @ColumnInfo(name = "itemcount")
    var itemcount: Int,

    @ColumnInfo(name = "rate")
    var rate: Float,

    @ColumnInfo(name = "owner_name")
    var owner_name : String,

    @ColumnInfo(name = "owner_image")
    var owner_image : Int

    )