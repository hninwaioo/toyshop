package com.shopping.toyu.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.shopping.toyu.R
import com.shopping.toyu.adapter.ViewPagerAdapter


class LandingPageActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var imageList: List<Int>
    lateinit var toy_title: List<String>
    lateinit var toy_about: List<String>

    lateinit var getstartedbtn : AppCompatButton
    lateinit var tabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
        getstartedbtn = findViewById<AppCompatButton>(R.id.get_started_btn)

        tabLayout = findViewById<TabLayout>(R.id.tab_layout)

        viewPager = findViewById(R.id.slide_ViewPager)
        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.old_toy
        imageList = imageList + R.drawable.old_toy
        imageList = imageList + R.drawable.old_toy
        imageList = imageList + R.drawable.old_toy
        imageList = imageList + R.drawable.old_toy

        toy_title = ArrayList<String>()
        toy_title = toy_title + "Swap Your Old Toy"
        toy_title = toy_title + "Swap Your Old Toy"
        toy_title = toy_title + "Swap Your Old Toy"
        toy_title = toy_title + "Swap Your Old Toy"
        toy_title = toy_title + "Swap Your Old Toy"

        toy_about = ArrayList<String>()
        toy_about = toy_about + "Lorem ipsum dolor sit amet, consectetur \n adipiscing elit. Proin tinciduct nunc non \n orci feugiat ornare."
        toy_about = toy_about + "Lorem ipsum dolor sit amet, consectetur \n adipiscing elit. Proin tinciduct nunc non \n orci feugiat ornare."
        toy_about = toy_about + "Lorem ipsum dolor sit amet, consectetur \n adipiscing elit. Proin tinciduct nunc non \n orci feugiat ornare."
        toy_about = toy_about + "Lorem ipsum dolor sit amet, consectetur \n adipiscing elit. Proin tinciduct nunc non \n orci feugiat ornare."
        toy_about = toy_about + "Lorem ipsum dolor sit amet, consectetur \n adipiscing elit. Proin tinciduct nunc non \n orci feugiat ornare."

        viewPagerAdapter = ViewPagerAdapter(this@LandingPageActivity, imageList,toy_title,toy_about)
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager, true);

        getstartedbtn.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}