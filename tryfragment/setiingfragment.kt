package com.example.tryfragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class setiingfragment : Fragment() {
    private lateinit var keluar : LinearLayout
    private lateinit var ubah : LinearLayout
    private lateinit var forto : ImageView
    private lateinit var nama : TextView
    private lateinit var tentang : LinearLayout
    private lateinit var riwayat : LinearLayout
    private lateinit var detai : LinearLayout



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setiingfragment, container, false)

        forto = view.findViewById(R.id.idfotoset)
        nama = view.findViewById(R.id.namasett)



        detai = view.findViewById(R.id.detail)
        ubah = view.findViewById(R.id.ubahprofil)
        tentang = view.findViewById(R.id.tentangkami)
        riwayat = view.findViewById(R.id.riwayatactive)

        tentang.setOnClickListener {
            val intent = Intent(activity, TetntangkamiActivity::class.java)
            activity?.startActivity(intent)

        }
        riwayat.setOnClickListener {
            val intent = Intent(activity, RiwayatactiveActivity::class.java)
            startActivity(intent)

        }
        detai.setOnClickListener {
            val intent = Intent(activity, Detairiwyatactivity::class.java)
            startActivity(intent)

        }

        ubah.setOnClickListener {
            val intent = Intent(activity, ubahdataActivity::class.java)
            startActivity(intent)

        }
        keluar = view.findViewById(R.id.keluar)


        keluar.setOnClickListener {
            val preferences: SharedPreferences = requireActivity().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(activity, loginActivity::class.java)
            startActivity(intent)
            Activity.F();
        }



            val sharedPreference =  activity?.getSharedPreferences("GET_TOKEN", Context.MODE_PRIVATE)
            val token = sharedPreference?.getString("token","")

            AndroidNetworking.get("https://grocery-api.tonsu.site/members")
                .setTag("members")
                .addHeaders("token", "Bearer" + " " + token)
                .setPriority(com.androidnetworking.common.Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        Log.d("response", response.toString())
                        try {
                            if (response.getString("success").equals("true")) {
                                val getJsonObject: JSONObject = response.getJSONObject("data")

                                Picasso.get()
                                    .load(getJsonObject.getString("photo_profile"))
                                    .into(forto)

                                nama.text = getJsonObject.getString("name")
                            }
                        } catch (e: JSONException) {
                            Log.d("onError", e.toString())
                        }
                    }

                    override fun onError(error: ANError) {
                        Log.d("onError", error.toString())
                    }
                })




        return view

    }







}