package com.example.tryfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class fragmenMember : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var datamemb: ArrayList<datagrid>
    private lateinit var sessionManager : SessionManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragmen_member, container, false)



        recyclerView = view.findViewById(R.id.remem)
        datamemb = ArrayList<datagrid>()

        getMember()


        return view
    }
        private fun getMember() {
            val sharedPreference =  activity?.getSharedPreferences("GET_TOKEN", Context.MODE_PRIVATE)
            val token = sharedPreference?.getString("token","")


            AndroidNetworking.get("https://grocery-api.tonsu.site/products/cart")
                .setPriority(Priority.LOW)
                .addHeaders("token", "Bearer" + " " + token)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        Log.d("response", response.toString())
                        try {
                            if(response.getString("success").equals("true")) {
                                val getJsonArray: JSONArray = response.getJSONArray("data")
                                for(i in 0 until getJsonArray.length()) {
                                    val item = getJsonArray.getJSONObject(i)
                                    val image = item.getString("image")

                                    Log.d("item", item.getString("image"))
                                    datamemb.add(
                                        datagrid(
                                            item.getInt("product_id"),
                                            image,
                                            item.getString("product_name"),
                                            item.getInt("original_price"),
                                            item.getInt("member_price"),
                                            item.getInt("quantity")
                                    )
                                    )



                                }
                                populatedata()
                            }
                        }
                        catch (e: JSONException) {
                            Log.d("onError", e.toString())
                        }

                    }
                    override fun onError(error: ANError) {
                        Log.d("onError", error.toString())
                    }

                })
        }



    private fun populatedata() {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = context?.let { griddadap (it,datamemb) }
    }



}
//
//
//        recyclerView = view.findViewById(R.id.memberfrag)
//        datamemb = ArrayList<datamember>()
//        val image = arrayOf(
//            R.drawable.img_10,
//            R.drawable.img_10,
//            R.drawable.img_10,
//            R.drawable.img_10,
//
//
//            )
//        val text = arrayOf(
//            "CARRY OUTER BROWN",
//            "CARRY OUTER BROWN",
//            "CARRY OUTER BROWN",
//            "CARRY OUTER BROWN",
//
//            )
//
//        val namangka = arrayOf(
//            "RP 249.000",
//            "RP 249.000",
//            "RP 249.000",
//            "RP 249.000",
//        )
//        val imgangka = arrayOf(
//            R.drawable.img_9,
//            R.drawable.img_9,
//            R.drawable.img_9,
//            R.drawable.img_9
//
//        )
//
//        val total = arrayOf(
//            "Total: RP 249.000",
//            "Total: RP 249.000",
//            "Total: RP 249.000",
//            "Total: RP 249.000"
//        )
//        val tbah = arrayOf(
//            R.drawable.img_12,
//            R.drawable.img_12,
//            R.drawable.img_12,
//            R.drawable.img_12
//        )
//        val sampah = arrayOf(
//            R.drawable.img_13,
//            R.drawable.img_13,
//            R.drawable.img_13,
//            R.drawable.img_13
//        )
//
//
//        for (i in image.indices) {
//            datamemb.add(
//                datamember(
//                    image[i],
//                    text[i],
//                    namangka[i],
//                    imgangka[i],
//                    total[i],
//                    tbah[i],
//                    sampah[i],
//
//                    )
//            )
//            populatedata()
//
//        }
//        return view
//
//    }
//
//
//        private fun populatedata() {
//            val layoutManager = LinearLayoutManager(activity)
//            layoutManager.reverseLayout = true
//            layoutManager.stackFromEnd = true
//            recyclerView.layoutManager = layoutManager
//            val adp = adapmember(this, datamemb)
//            recyclerView.adapter = adp
//        }
//
//
//}
