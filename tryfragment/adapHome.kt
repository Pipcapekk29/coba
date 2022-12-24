package com.example.tryfragment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class adapHome(private val context: Context,private val datahome: ArrayList<DataHome>) : RecyclerView.Adapter<adapHome.MyViewHolder>() {


//

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fetch_home, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = datahome[position]
        // get id
        val getId = currentItem.id
        val getImage = currentItem.imageHomeAdap


        //load image using picasso
        Picasso.get()
            .load(getImage)
            .into(holder.imagehome)


        holder.imagehome.setOnClickListener{
            val intent = Intent(context,Detailcatalog::class.java)
            intent.putExtra("image",getImage)
            context.startActivity(intent)


        }

        holder.buttn.setOnClickListener{
            datahome.removeAt(position)
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, datahome.size)
        }


        //  holder.imagehome.setImageResource(currentItem.imageHomeAdap)



    }

    override fun getItemCount(): Int {
        return datahome.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagehome: ImageView = itemView.findViewById(R.id.fetch_home_image)
        val buttn : Button = itemView.findViewById(R.id.hapusre)


    }
}