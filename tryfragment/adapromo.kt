package com.example.tryfragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

class adapromo(val context: Context, val datapromo: ArrayList<dataPromo>) : RecyclerView.Adapter<adapromo.MyViewHolder>() {


//

    private lateinit var sessionManager: SessionManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fetch_proomo, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = datapromo[position]

        val id = currentItem.id
        val getImage = currentItem.imagepromo
//        val gettitle = currentItem.title
//        val getnama = currentItem.nama
//        val getharga = currentItem.harga

        Picasso.get()
            .load(getImage)
            .into(holder.Image)



        holder.btn.setOnClickListener {
            val jsonObject = JSONObject()
            try {
                jsonObject.put("product_id", currentItem.id)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            val sharedPreference =  context.getSharedPreferences("GET_TOKEN", Context.MODE_PRIVATE)
            val token = sharedPreference?.getString("token","")

            AndroidNetworking.post("https://grocery-api.tonsu.site/products/activate")
                .setTag("activate")
                .addJSONObjectBody(jsonObject) // posting json
                .addHeaders("token", "Bearer" + " " + token)
                .setPriority(com.androidnetworking.common.Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        Log.d("response", response.toString())
                        try {
                            if(response.getString("success").equals("true")) {
                                Toast.makeText(context, "Produk Berhasil Diaktifkan", Toast.LENGTH_SHORT).show()
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







    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val Image : ImageView = itemView.findViewById(R.id.img_promo)
        val title : TextView = itemView.findViewById(R.id.textpromo)
        val nama : TextView = itemView.findViewById(R.id.namapromo)
        val harga : TextView = itemView.findViewById(R.id.angkapromo)
        val btn : LinearLayout = itemView.findViewById(R.id.aktif)


    }

    override fun getItemCount(): Int {
        return datapromo.size
    }

}