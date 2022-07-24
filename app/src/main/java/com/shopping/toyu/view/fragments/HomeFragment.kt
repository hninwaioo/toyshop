package com.shopping.toyu.view.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.shopping.toyu.R
import com.shopping.toyu.adapter.ImageViewPagerAdapter
import com.shopping.toyu.adapter.ToyItemListAdapter
import com.shopping.toyu.database.AddToyItemDao
import com.shopping.toyu.database.AddToyItemDatabase
import com.shopping.toyu.model.ToyDataListItem
import com.shopping.toyu.view.activities.ToyItemDetailActivity
import kotlinx.android.synthetic.main.activity_toy_item_detail.view.*
import java.util.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(), ToyItemListAdapter.ItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewPager: ViewPager
    lateinit var imageviewPagerAdapter: ImageViewPagerAdapter
    lateinit var imageList: List<Int>

    lateinit var db: AddToyItemDatabase
    lateinit var dao: AddToyItemDao

    private lateinit var  toyItemListAdapter : ToyItemListAdapter
    private var dataList = mutableListOf<ToyDataListItem>()

    lateinit var itemListView : RecyclerView
    lateinit var searchItems : EditText

    private var position_id : Int = 0
    var CURRENT_POSITION_KEY = "0"

    var currentPage = 0
    var timer: Timer? = null
    val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed
    val PERIOD_MS: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        itemListView = view.findViewById(R.id.item_list_view)
        searchItems = view.findViewById(R.id.search_items)

        viewPager = view.findViewById<ViewPager>(R.id.main_viewpager)
        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.robort
        imageList = imageList + R.drawable.train_icon
        imageList = imageList + R.drawable.game
        imageList = imageList + R.drawable.crocodile
        imageList = imageList + R.drawable.aeroplane

        imageviewPagerAdapter = ImageViewPagerAdapter(this.requireContext(), imageList)
        viewPager.adapter = imageviewPagerAdapter

        /*After setting the adapter use the timer */
        /*After setting the adapter use the timer */
        val handler = Handler()
        val Update = Runnable {
            if (currentPage === 5 - 1) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
        }

        timer = Timer() // This will create a new Thread

        timer!!.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)

        AddData()

        searchItems.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                filter(searchItems.toString());
                toyItemListAdapter.notifyDataSetChanged()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                // TODO Auto-generated method stub
            }
            override fun afterTextChanged(s: Editable) {
                // filter your list from your input
                filter(s.toString())
                toyItemListAdapter.notifyDataSetChanged()
            }
        })

        return view
    }

    fun AddData() {

        db = AddToyItemDatabase.getInstance(this.requireContext())
        dao = db.getAllToyItemDao()

        dao.addToyItems(ToyDataListItem(title = "Mini Bot",release = "New", price = 25.0F, description = "Lorem ipsum dolor sit amet, consectetur \n adipiscing elit. Proin tinciduct nunc non \n orci feugiat ornare.", category = "Toy", image = R.drawable.robort, itemcount = 5, rate = 2.5F, owner_name = "Kelly", owner_image = R.drawable.owner_1))
        dao.addToyItems(ToyDataListItem(title = "Train",release = "Exclusive", price = 35.0F, description = "Lorem ipsum dolor sit amet, consectetur \n adipiscing elit. Proin tinciduct nunc non \n orci feugiat ornare.", category = "Toy", image = R.drawable.train_icon, itemcount = 4, rate = 3.5F,owner_name = "Mikel", owner_image = R.drawable.owner_2))
        dao.addToyItems(ToyDataListItem(title = "Controller",release = "New", price = 70.0F, description = "Lorem ipsum dolor sit amet, consectetur \n adipiscing elit. Proin tinciduct nunc non \n orci feugiat ornare.", category = "Toy", image = R.drawable.game, itemcount = 6, rate = 4.5F,owner_name = "Kelly", owner_image = R.drawable.owner_1))
        dao.addToyItems(ToyDataListItem(title = "Crocodile",release = "Limited", price = 55.0F, description = "Lorem ipsum dolor sit amet, consectetur \n adipiscing elit. Proin tinciduct nunc non \n orci feugiat ornare.", category = "Toy", image = R.drawable.crocodile, itemcount = 3, rate = 2.0F,owner_name = "Mikel", owner_image = R.drawable.owner_2))
        dao.addToyItems(ToyDataListItem(title = "Aeroplane",release = "New", price = 60.0F, description = "Lorem ipsum dolor sit amet, consectetur \n adipiscing elit. Proin tinciduct nunc non \n orci feugiat ornare.", category = "Toy", image = R.drawable.aeroplane, itemcount = 2, rate = 5.0F,owner_name = "Kelly", owner_image = R.drawable.owner_1))

        dao.allCartItem()
        Log.e("DAO==>",dao.getAllToyItems().toString())

        itemListView.layoutManager = GridLayoutManager(this.requireContext(),2)
        toyItemListAdapter = ToyItemListAdapter(this.requireContext())
        itemListView.adapter = toyItemListAdapter

        dataList.addAll(dao.getAllToyItems());
        toyItemListAdapter.setDataList(dataList)

        itemListView.setOnClickListener {
            goToDetail(itemList = dataList);
            Log.e("DetailPage==>","Detail")
        }

        toyItemListAdapter.setOnClickListener(object : ToyItemListAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                position_id = position+1
                Log.e("PositionId ==>",position_id.toString())
                goToDetail(itemList = dataList);
            }
        })

    }

    fun filter(text: String?) {
        val found_items: MutableList<ToyDataListItem> = ArrayList()
        for (d in dataList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.title.contains(text!!)) {
                found_items.add(d)
            }
        }
        //update recyclerview
        toyItemListAdapter.updateList(found_items)

    }

    private fun goToDetail(itemList: List<ToyDataListItem>) {

        for (item in itemList) {
            if (item.id == position_id) {
                val intent = Intent(this.requireContext(), ToyItemDetailActivity::class.java)
                intent.putExtra("item_id",item.id)
                intent.putExtra("item_title", item.title)
                intent.putExtra("item_release",item.release)
                intent.putExtra("item_price",item.price)
                intent.putExtra("item_category", item.category)
                intent.putExtra("item_description",item.description)
                intent.putExtra("item_image",item.image)
                intent.putExtra("item_rate",item.rate)
                intent.putExtra("item_count",item.itemcount)

                intent.putExtra("item_owner_name",item.owner_name)
                intent.putExtra("item_owner_image",item.owner_image)

                startActivity(intent)
                Log.e("SuccessId","Success_Id")
                return
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(view: View?, position: Int) {
//        Toast.makeText(this, "You clicked " + toyItemListAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
//        goToDetail(itemList = dataList);

    }
}