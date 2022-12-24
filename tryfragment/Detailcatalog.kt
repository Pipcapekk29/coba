package com.example.tryfragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.tryfragment.databinding.ActivityDetailcatalogBinding
import com.squareup.picasso.Picasso

class Detailcatalog : AppCompatActivity() {
    private lateinit var binding: ActivityDetailcatalogBinding
    private lateinit var imageV: ImageView
    private lateinit var btn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailcatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.run { shimmerDetailCatalog.startShimmer() }

        imageV = findViewById(R.id.imagee)
        val id = this.intent.getStringExtra("id")?.toInt()
        val image1 = this.intent.getStringExtra("image")

        Log.d("image katalog", image1.toString())


        Picasso.get()
            .load(image1)
            .into(imageV)
        btn = findViewById(R.id.butttt)

        btn.setOnClickListener {
//            val intent = Intent(this, fragmentActivity::class.java)
//            startActivity(intent)
            Toast.makeText(this@Detailcatalog,"Berhasil", Toast.LENGTH_SHORT).show()
        }






    }

}