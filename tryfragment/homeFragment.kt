package com.example.tryfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class homeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataListHome: ArrayList<DataHome>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)




        recyclerView = view.findViewById(R.id.homere)
//        reycylerView.setHasFixedSize(true)
//        reycylerView.layoutManager = manager
        dataListHome = ArrayList<DataHome>()

        getData()



//        val image1 = arrayOf(
//            R.drawable.img_1,
//            R.drawable.img_3,
//            R.drawable.img_2,
//            R.drawable.img_3,
//
//
//            )
//
//        for (i in image1.indices) {
//            dataListHome.add(
//                DataHome(
//                    image1[i]
//                )
//
//            )
//            populatedata()
//
//
//        }
        return view


    }
    private fun getData(){
        AndroidNetworking.get("https://grocery-api.tonsu.site/products/katalog")
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
                                dataListHome.add(
                                    DataHome(
                                        item.getInt("id"),
                                        item.getString("image")
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
        recyclerView.adapter = context?.let { adapHome(it,dataListHome) }
    }


}