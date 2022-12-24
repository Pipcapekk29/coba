package com.example.tryfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapmember(val context: RiwayatactiveActivity, val datamember: ArrayList<datamember>) : RecyclerView.Adapter<adapmember.MyViewHolder>() {

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val UsernameUser: ImageView = itemView.findViewById(R.id.textNama1)
        val UserPos: TextView = itemView.findViewById(R.id.posUser)
        val Userhuruf: TextView = itemView.findViewById(R.id.textHuruf1)
        val UserPertanyaan: TextView = itemView.findViewById(R.id.pertanyaan)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate( R.layout.fetch_setting, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = datamember[position]

        holder.UsernameUser.setImageResource(currentItem.nama)

    }

    override fun getItemCount(): Int {
        return  datamember.size
    }

}