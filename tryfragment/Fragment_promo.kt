package com.example.tryfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONException
import org.json.JSONObject

class Fragment_promo : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataprmo: ArrayList<dataPromo>
    private lateinit var btn : LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_promo, container, false)





        recyclerView = view.findViewById(R.id.promofrag)
        dataprmo = ArrayList<dataPromo>()




        getData()


        return view

    }   private fun getData(){
        AndroidNetworking.get("https://grocery-api.tonsu.site/products/promo")
            .setTag("katalog")
            .setPriority(com.androidnetworking.common.Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.d("response", response.toString())
                    try {
                        if(response.getString("success").equals("true")) {
                            val getJsonArray = response.getJSONArray("data")
                            for(i in 0 until getJsonArray.length()) {
                                val item = getJsonArray.getJSONObject(i)
//                                val image = item.getString("image")
//
//                                Log.d("item", item.getString("image"))
                                dataprmo.add(
                                    dataPromo(
                                        item.getInt("id"),
                                        item.getString("image"),
                                        item.getString("product_name"),
                                        item.getString("unit"),
                                        item.getInt("original_price")

                                    )
                                )
//
//                                recyclerView.layoutManager = LinearLayoutManager(activity)
//                                recyclerView.adapter = adapHome(dataListHome)
                            }
                            populatedata()
                        }
                    } catch(e: JSONException) {
                        Log.d("onError", e.toString())
                    }
                }

                override fun onError(error: ANError) {
                    Log.d("onError", error.toString())
                }
            })



    }



    private fun populatedata() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = activity?.let { adapromo(it,dataprmo) }
    }


}