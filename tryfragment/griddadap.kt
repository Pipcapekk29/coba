package com.example.tryfragment

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

class griddadap(val context: Context, val datahome: ArrayList<datagrid>) : RecyclerView.Adapter<griddadap.MyViewHolder>() {


//

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fetch_member, parent, false)
        return MyViewHolder(itemView)
    }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val currentItem = datahome[position]

            val hargaasli = currentItem.harga
            val hargamem = currentItem.hargamem

            Picasso.get()
                .load(currentItem.imageG)
                .into(holder.imageq)


            val jumlahPesenan = currentItem.jumlah11.toString()
            val Totalharga = hargaasli - hargamem
            var num = 0 + jumlahPesenan.toInt()
            holder.plusss.setOnClickListener {
                num++
                Log.d("NUM plus", num.toString())
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("product_id", currentItem.id)
                    jsonObject.put("quantity", num)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                val sharedPreference =  context.getSharedPreferences("GET_TOKEN", Context.MODE_PRIVATE)
                val token = sharedPreference?.getString("token","")

                AndroidNetworking.post("https://grocery-api.tonsu.site/products/activate")
                    .addJSONObjectBody(jsonObject)
                    .addHeaders("Content-Type", "application/json")
                    .setPriority(Priority.MEDIUM)
                    .addHeaders("token", "Bearer" + " " + token)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject) {
                            Log.d("response", response.toString())
                            try {

                            } catch(e: JSONException) {
                                Log.d("onError", e.toString())
                            }
                        }

                        override fun onError(error: ANError) {
                            Log.d("onError", error.toString())
                        }
                    })
                val total = Totalharga * num
                Log.d("Total tambah", currentItem.harga.toString())
                holder.total.text = "Rp" + " " + total.toString()
                holder.jumlah.text = num.toString()





            }
    //        holder.removeItem.setOnClickListener {
    //            num--
    //            if (num < 1){
    //                num = 0
    //                memberList.removeAt(holder.adapterPosition)
    //                notifyDataSetChanged()
    //            }
    //            val jsonObject = JSONObject()
    //            try {
    //                jsonObject.put("product_id", getId)
    //                jsonObject.put("quantity", num)
    //            } catch (e: JSONException) {
    //                e.printStackTrace()
    //            }
    //
    //            val sharedPreference =  context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
    //            val token = sharedPreference?.getString("token","")
    //
    //            AndroidNetworking.post("https://grocery-api.tonsu.site/products/activate")
    //
    //                .addJSONObjectBody(jsonObject)
    //                .addHeaders("Content-Type", "application/json")
    //                .setPriority(Priority.MEDIUM)
    //                .addHeaders("token", "Bearer" + " " + token)
    //                .build()
    //                .getAsJSONObject(object : JSONObjectRequestListener {
    //                    override fun onResponse(response: JSONObject) {
    //                        Log.d("ini respon register", response.toString())
    //                        try {
    //                        } catch (e: JSONException) {
    //                            e.printStackTrace()
    //                            Toast.makeText(context, "kesalahan", Toast.LENGTH_SHORT).show()
    //                        }
    //                    }
    //                    override fun onError(error: ANError) {
    //                        // handle error
    //                    }
    //                })
    //
    //
    ////            val hitungHargaDiskon = currentItem.hargaDiskon * num
    ////            val hitungHargaMember = currentItem.hargaMember * num
    //            val total = totalHarga * num
    //            holder.jumlahMember.text = num.toString()
    //            holder.totalMember.text = "Total Rp" + " " + total.toString()
    //
    //
    //
    //
    //        }



            holder.min.setOnClickListener {
                num--
                if (num < 1) {
                    num = 0
                    datahome.removeAt(holder.adapterPosition)
                    notifyDataSetChanged()
                }
                Log.d("NUM min", num.toString())
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("product_id", currentItem.id)
                    jsonObject.put("quantity", num)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                val sharedPreference =  context.getSharedPreferences("GET_TOKEN", Context.MODE_PRIVATE)
                val token = sharedPreference?.getString("token","")

                AndroidNetworking.post("https://grocery-api.tonsu.site/products/activate")
                    .addJSONObjectBody(jsonObject)
                    .addHeaders("Content-Type", "application/json")
                    .setPriority(Priority.MEDIUM)
                    .addHeaders("token", "Bearer" + " " + token)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject) {
                            Log.d("ini respon register", response.toString())
                            try {
                            } catch (e: JSONException) {
                                e.printStackTrace()
                                Toast.makeText(context, "kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onError(error: ANError) {
                            // handle error
                        }
                    })

                val total = Totalharga * num
                holder.jumlah.text = num.toString()
                holder.total.text = "Rp" + " " + total.toString()

            }



            // load image

            holder.title.text = currentItem.title
            holder.price.text = "Rp " + currentItem.harga.toString()
            holder.jumlah.text = currentItem.jumlah11.toString()



    //        holder.title.text = currentItem.title
    //        holder.price.text = "Rp " + currentItem.price.toString()
    //        holder.discount.text = "Rp " + currentItem.discount.toString()
    //        holder.discount.setPaintFlags(holder.discount.getPaintFlags() or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG)


            holder.btnDelete.setOnClickListener {
                holder.jumlah.text = "0"
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("product_id", currentItem.id)
                    jsonObject.put("quantity", 0)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                val sharedPreference =  context.getSharedPreferences("GET_TOKEN", Context.MODE_PRIVATE)
                val token = sharedPreference?.getString("token","")

                AndroidNetworking.post("https://grocery-api.tonsu.site/products/activate")
                .setTag("activate")
                .addJSONObjectBody(jsonObject) // posting json
                .addHeaders("token", "Bearer" + " " +token)
                .setPriority(com.androidnetworking.common.Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        Log.d("response", response.toString())
                        try {
                            if(response.getString("success").equals("true")) {
                                datahome.removeAt(position)
                                notifyDataSetChanged()
                                Toast.makeText(context, "Produk Berhasil Dihapus", Toast.LENGTH_SHORT).show()
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

        override fun getItemCount(): Int {
            return datahome.size
        }
        class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val imageq: ImageView = itemView.findViewById(R.id.imagemem)
            val title: TextView = itemView.findViewById(R.id.textmem)
            val price: TextView = itemView.findViewById(R.id.haragamem)
            val btnDelete: ImageView = itemView.findViewById(R.id.hahu)
            val jumlah : TextView = itemView.findViewById(R.id.jumlahnum)
            val min : ImageView = itemView.findViewById(R.id.minus)
            val plusss : ImageView = itemView.findViewById(R.id.pluss)
            val total : TextView = itemView.findViewById(R.id.total)
        }
    }
