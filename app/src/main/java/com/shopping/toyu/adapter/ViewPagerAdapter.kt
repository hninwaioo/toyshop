package com.shopping.toyu.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.shopping.toyu.R
import java.util.*

class ViewPagerAdapter(val context: Context, val imageList: List<Int>, val toy_title: List<String>, val toy_about: List<String>) : PagerAdapter(){
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
        val itemView: View = mLayoutInflater.inflate(R.layout.slide_view_item, container, false)
        val old_toy_imageView: ImageView = itemView.findViewById<View>(R.id.slide_oldToy) as ImageView
        old_toy_imageView.setImageResource(imageList.get(position))
        val old_toy_title : TextView = itemView.findViewById<View>(R.id.old_toy_title) as TextView
        old_toy_title.setText(toy_title.get(position))

        val old_toy_about : TextView = itemView.findViewById<View>(R.id.old_toy_about) as TextView
        old_toy_about.setText(toy_about.get(position))

        Objects.requireNonNull(container).addView(itemView)
        return itemView

    }
    // on below line we are creating a destroy item method.
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // on below line we are removing view
        container.removeView(`object` as RelativeLayout)
    }
}