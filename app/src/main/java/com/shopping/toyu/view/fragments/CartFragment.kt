package com.shopping.toyu.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shopping.toyu.R
import com.shopping.toyu.viewmodel.AddCartToyItemViewModel
import com.shopping.toyu.viewmodel.AddCartToyItemViewModelFactory
import androidx.lifecycle.Observer
import com.shopping.toyu.adapter.AddCartToyItemListAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CartFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var position_id : Int = 0
    lateinit var addCartToyItemRecyclerview : RecyclerView
    lateinit var needToPayId : TextView
    lateinit var orderNow : TextView
    private lateinit var addCartToyItemViewModel: AddCartToyItemViewModel
    private lateinit var  addCartToyItemListadapter : AddCartToyItemListAdapter

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
        val view: View = inflater.inflate(R.layout.fragment_cart, container, false)

        addCartToyItemRecyclerview = view.findViewById<RecyclerView>(R.id.add_cart_toy_item_recyclerview)
        needToPayId = view.findViewById<TextView>(R.id.need_to_pay_id)
        orderNow = view.findViewById<TextView>(R.id.order_now)

        val modelfactory= AddCartToyItemViewModelFactory(application = this.requireActivity().application);

        addCartToyItemViewModel = ViewModelProvider(this,modelfactory).get(AddCartToyItemViewModel::class.java)

        // Specify layout for recycler view
        val linearLayoutManager = LinearLayoutManager(
            this.requireContext(), RecyclerView.VERTICAL,false)
        addCartToyItemRecyclerview.layoutManager = linearLayoutManager

        addCartToyItemViewModel.allCartToyItem.observe(viewLifecycleOwner, Observer{ cartItem->
            // Data bind the recycler view

            addCartToyItemRecyclerview.adapter = AddCartToyItemListAdapter(addCartToyItemViewModel,cartItem)
            Log.e("CartItem==>",cartItem.toString())

            val costs  = cartItem.sumByDouble { it.totalprice.toDouble() }
            var costs2digits:Double = String.format("%.2f", costs).toDouble()

            needToPayId.text = "You need to pay $ ${costs2digits}"
            Log.e("SubTotal==>",costs2digits.toString())

        })
        // Observe the model
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}