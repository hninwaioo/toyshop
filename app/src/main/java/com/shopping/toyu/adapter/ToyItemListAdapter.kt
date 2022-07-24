package com.shopping.toyu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shopping.toyu.R
import com.shopping.toyu.model.ToyDataListItem

class ToyItemListAdapter(var context: Context) : RecyclerView.Adapter<ToyItemListAdapter.ViewHolder>() {

    private lateinit var mListener : onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener : onItemClickListener){
        mListener = listener
    }

    var dataList = emptyList<ToyDataListItem>()
    internal fun setDataList(dataList: List<ToyDataListItem>) {
        this.dataList = dataList
    }

    // Provide a direct reference to each of the views with data items
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var toy_image: ImageView
        var toy_release: TextView
        var toy_title: TextView

        init {
            toy_image = itemView.findViewById(R.id.toy_image)
            toy_release = itemView.findViewById(R.id.update_release_id)
            toy_title = itemView.findViewById(R.id.toy_kind_id)
        }

    }
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToyItemListAdapter.ViewHolder {

        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.toy_items_view, parent, false)
        return ViewHolder(view)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ToyItemListAdapter.ViewHolder, position: Int) {

        // Get the data model based on position
        var data = dataList[position]

        // Set item views based on your views and data model
        holder.toy_release.text = data.release
        holder.toy_title.text = data.title
        holder.toy_image.setImageResource(data.image)

        holder.itemView.setOnClickListener {
            mListener.onItemClick(position)
        }
    }
    //  total count of items in the list
    override fun getItemCount() = dataList.size

    fun updateList(list: MutableList<ToyDataListItem>) {
        dataList = list
        notifyDataSetChanged()
    }
    fun getItem(id: Int): List<ToyDataListItem> {
        return listOf(dataList.get(id))
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}