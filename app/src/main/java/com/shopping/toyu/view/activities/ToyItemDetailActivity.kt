package com.shopping.toyu.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.shopping.toyu.R
import com.shopping.toyu.model.AddCartToyItemModel
import com.shopping.toyu.model.ToyDataListItem
import com.shopping.toyu.viewmodel.AddCartToyItemViewModel
import com.shopping.toyu.viewmodel.AddCartToyItemViewModelFactory
import org.jetbrains.anko.doAsync

class ToyItemDetailActivity : AppCompatActivity() {

    lateinit var backFinish : ImageView
    lateinit var showToyImage : ImageView
    lateinit var showToyPrice : TextView
    lateinit var showToyTitle : TextView
    lateinit var ratingStar : RatingBar
    lateinit var rateValue : TextView
    lateinit var toyDescription : TextView
    lateinit var showItemPrice : TextView

    lateinit var ownerNameDetail : TextView
    lateinit var ownerImageDetail: ImageView

    var item_Id : Int = 0
    lateinit var item_Title : String
    lateinit var item_Release: String
    var item_Price : Float = 0.0F
    lateinit var item_Category : String
    lateinit var item_Description : String
    var item_Image : Int = 0
    var item_Rate : Float = 0.0F
    var item_Count : Int = 0
    lateinit var itemOwnerName : String
    var itemOwnerImage : Int = 0

    private lateinit var Minus_count : TextView
    private lateinit var Plus_count : TextView
    private lateinit var Count_number : TextView

    private lateinit var toyitems: ToyDataListItem
    private var count = 1
    private var item_count_price : Float = 0.0f

    lateinit var addCartBtn: AppCompatButton;
    private lateinit var addCartToyItemViewModel: AddCartToyItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toy_item_detail)

        val intent = intent
        item_Id = intent.getIntExtra("item_id",0)
        item_Title = intent.getStringExtra("item_title").toString()
        item_Release = intent.getStringExtra("item_release").toString()
        item_Price = intent.getFloatExtra("item_price",0.0F)
        item_Category = intent.getStringExtra("item_category").toString()
        item_Description = intent.getStringExtra("item_description").toString()
        item_Image = intent.getIntExtra("item_image",0)
        item_Rate = intent.getFloatExtra("item_rate",0.0F)
        item_Count = intent.getIntExtra("item_count",0)
        Log.e("ItemCount==>",item_Count.toString())
        itemOwnerName = intent.getStringExtra("item_owner_name").toString()
        itemOwnerImage = intent.getIntExtra("item_owner_image",0)

        backFinish = findViewById<ImageView>(R.id.back_finish)
        showToyImage = findViewById<ImageView>(R.id.show_toy_image)
        showToyPrice = findViewById<TextView>(R.id.show_toy_price)
        showToyTitle = findViewById<TextView>(R.id.show_toy_title)
        ratingStar = findViewById<RatingBar>(R.id.rating_star)
        rateValue = findViewById<TextView>(R.id.rate_value)
        toyDescription = findViewById<TextView>(R.id.toy_description)
        addCartBtn = findViewById<AppCompatButton>(R.id.add_cart_btn)
        showItemPrice = findViewById<TextView>(R.id.show_item_price)

        ownerNameDetail = findViewById<TextView>(R.id.owner_name_detail)
        ownerImageDetail = findViewById<ImageView>(R.id.owner_image_detail)

        backFinish.setOnClickListener {
            finish()
        }
        ownerNameDetail.text = itemOwnerName
        Glide.with(this).load(itemOwnerImage).into(ownerImageDetail)

        showToyTitle.text = item_Title

        showToyPrice.text = "$ $item_Price"
        showItemPrice.text = "$ $item_Price"
        Log.e("ItemPrice==>",item_Price.toString())

        toyDescription.text = item_Description
        Log.e("ItemDescription==>",item_Description)
        rateValue.text = "$item_Rate"
        ratingStar.rating = item_Rate

        Glide.with(this).load(item_Image).into(showToyImage)

        Minus_count = findViewById<TextView>(R.id.minus_count)
        Plus_count = findViewById<TextView>(R.id.plus_count)
        Count_number = findViewById<TextView>(R.id.count_number)

        item_count_price = item_Price
        Minus_count.setOnClickListener {

            if (count > 1){
                count --
                item_count_price = item_Price * count
                showItemPrice.text = "$ ${item_count_price}"
                Log.e("CountM==>",item_count_price.toString())
                Count_number.text = count.toString()
            }
        }

        if (count==1){
            Count_number.text = count.toString()

            Plus_count.setOnClickListener {
                if (count>=item_Count){
                    Toast.makeText(this,"No Item Avaliable", Toast.LENGTH_SHORT).show()

                }else{
                    count ++
                    item_count_price= item_Price * count
                    showItemPrice.text = "$ ${item_count_price}"
                    Log.e("CountP==>",item_count_price.toString())
                    Count_number.text = count.toString()
                }
            }
        }

        val modelfactory= AddCartToyItemViewModelFactory(application);

        addCartToyItemViewModel = ViewModelProvider(this,modelfactory).get(AddCartToyItemViewModel::class.java)

        addCartBtn.setOnClickListener {
            AddToCartAlert();
        }

    }

    fun AddToCartAlert(){

        val builder = AlertDialog.Builder(this,R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.custom_alert_dialog,null)
        val  ok_button = view.findViewById<TextView>(R.id.ok_btn)
        val cancel_btn = view.findViewById<TextView>(R.id.cancel_btn)
        builder.setView(view)
        ok_button.setOnClickListener {
            doAsync {
                addCartToyItemViewModel.insert(AddCartToyItemModel(null,
                    item_Image, item_Title,item_Release,item_count_price,count,itemOwnerName,itemOwnerImage))
                Log.e("SuccessData==>","SuccessData")
            }

            Toast.makeText(this,"SuccessFull Add To Cart !",Toast.LENGTH_SHORT).show()
            builder.dismiss()
            finish()
        }
        cancel_btn.setOnClickListener {
            builder.dismiss()
        }
        builder.show()
    }
}