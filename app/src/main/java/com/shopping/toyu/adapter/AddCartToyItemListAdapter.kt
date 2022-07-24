package com.shopping.toyu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.toyu.R
import com.shopping.toyu.database.AddCartToyItemDao
import com.shopping.toyu.model.AddCartToyItemModel
import com.shopping.toyu.viewmodel.AddCartToyItemViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddCartToyItemListAdapter(
    var addCartToyItemViewModel: AddCartToyItemViewModel,
                                    val cartitem: List<AddCartToyItemModel>)
    : RecyclerView.Adapter<AddCartToyItemListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : AddCartToyItemListAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_toy_item_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AddCartToyItemListAdapter.ViewHolder, position: Int) {
        holder.totalItemPrice.text = "$ ${cartitem[position].totalprice}"
        holder.chooseItemCount.text = "items ${cartitem[position].itemcount}"
        holder.cartToyReleaseId.text = cartitem[position].toy_release
        holder.cartToyKindId.text = cartitem[position].title
        holder.ownerName.text = cartitem[position].owner_name
        Glide.with(holder.itemView.context).load(cartitem[position].image).into(holder.cartToyImage)
        Glide.with(holder.itemView.context).load(cartitem[position].owner_image).into(holder.ownerImage)

        holder.deleteItem.setOnClickListener {
            deleteCartItem(position)
        }
    }

    override fun getItemCount(): Int {
        return cartitem.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var cartToyImage=itemView.findViewById<ImageView>(R.id.cart_toy_image)
        var cartToyReleaseId = itemView.findViewById<TextView>(R.id.cart_toy_release_id)
        var cartToyKindId = itemView.findViewById<TextView>(R.id.cart_toy_kind_id)
        var ownerName = itemView.findViewById<TextView>(R.id.owner_name)
        var ownerImage = itemView.findViewById<ImageView>(R.id.owner_image)
        var chooseItemCount = itemView.findViewById<TextView>(R.id.choose_item_count)
        var totalItemPrice = itemView.findViewById<TextView>(R.id.total_item_price)

        var deleteItem = itemView.findViewById<ImageView>(R.id.delete_item)
    }
    private fun deleteCartItem(position: Int) {
        val item = cartitem[position]
        (cartitem as MutableList).remove(item)
        notifyItemChanged(position)
        GlobalScope.launch {
            addCartToyItemViewModel.delete(item.id!!)

        }
    }
}