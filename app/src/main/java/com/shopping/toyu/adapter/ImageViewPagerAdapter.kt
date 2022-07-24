package com.shopping.toyu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.shopping.toyu.R
import com.shopping.toyu.view.fragments.HomeFragment
import java.util.*

class ImageViewPagerAdapter(val context: Context, val imageList: List<Int>): PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val mLayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


        // on below line we are inflating our custom
        // layout file which we have created.
        val itemView: View = mLayoutInflater.inflate(R.layout.image_view_items, container, false)
        val old_toy_imageView: ImageView = itemView.findViewById<View>(R.id.slide_toy_id) as ImageView
        old_toy_imageView.setImageResource(imageList.get(position))

        Objects.requireNonNull(container).addView(itemView)
        return itemView

    }

    // on below line we are creating a destroy item method.
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // on below line we are removing view
        container.removeView(`object` as RelativeLayout)
    }
}