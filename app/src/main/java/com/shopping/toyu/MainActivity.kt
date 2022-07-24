package com.shopping.toyu.view

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.shopping.toyu.R
import com.shopping.toyu.view.fragments.CartFragment
import com.shopping.toyu.view.fragments.FavouriteFragment
import com.shopping.toyu.view.fragments.HomeFragment
import com.shopping.toyu.view.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var mainActivity: MainActivity? = null
    var fm: FragmentManager? = null
    var context: Context? = null

    var refreshItem: MenuItem? = null
    lateinit var toolbar: Toolbar
    lateinit var drawerLeft : ImageView
    lateinit var toyuIcon : ImageView
    lateinit var notiIcon : ImageView

    private val menuItemId = intArrayOf(
        R.id.nav_home,
        R.id.nav_favourite,
        R.id.nav_cart,
        R.id.nav_profile,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivity = this

        drawerLeft = findViewById<View>(R.id.drawer_left) as ImageView
        toyuIcon = findViewById<View>(R.id.toyu_icon_id) as ImageView
        notiIcon = findViewById<View>(R.id.noti_icon_id) as ImageView

        // Setup ToolBar
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val bottomNavigationView = findViewById<View>(R.id.nav_view) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        if (savedInstanceState == null) {
            toolbar.setBackgroundColor(resources.getColor(R.color.main_color))
            drawerLeft.setImageDrawable(resources.getDrawable(R.drawable.ic_draw))
            toyuIcon.setImageDrawable(resources.getDrawable(R.drawable.splash_toyu))
            notiIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_icons))
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                .commit()
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.nav_home -> {
                val fragment = HomeFragment()
                toolbar.setBackgroundColor(resources.getColor(R.color.main_color))
                drawerLeft.setImageDrawable(resources.getDrawable(R.drawable.ic_draw))
                toyuIcon.setImageDrawable(resources.getDrawable(R.drawable.splash_toyu))
                notiIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_icons))

                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_favourite -> {
                toolbar.setBackgroundColor(resources.getColor(R.color.fourth_main_color))
                drawerLeft.setImageDrawable(resources.getDrawable(R.drawable.ic_drawer))
                toyuIcon.setImageDrawable(resources.getDrawable(R.drawable.letter_1))
                notiIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_noti))
                val fragment = FavouriteFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_cart -> {
                toolbar.setBackgroundColor(resources.getColor(R.color.fourth_main_color))
                drawerLeft.setImageDrawable(resources.getDrawable(R.drawable.ic_drawer))
                toyuIcon.setImageDrawable(resources.getDrawable(R.drawable.letter_1))
                notiIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_noti))
                val fragment = CartFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                toolbar.setBackgroundColor(resources.getColor(R.color.fourth_main_color))
                drawerLeft.setImageDrawable(resources.getDrawable(R.drawable.ic_drawer))
                toyuIcon.setImageDrawable(resources.getDrawable(R.drawable.letter_1))
                notiIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_noti))
                val fragment = ProfileFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}