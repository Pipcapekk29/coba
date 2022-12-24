package com.example.tryfragment

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.example.tryfragment.databinding.ActivityUbahdataBinding

class ubahdataActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUbahdataBinding
    private lateinit var  btn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_ubahdata)
        binding = ActivityUbahdataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btn = findViewById(R.id.btnubahdata)

        btn.setOnClickListener{
            Toast.makeText(this,"Berhasil ubah data",Toast.LENGTH_SHORT).show()
        }


        binding.balkkesetting.setOnClickListener {
            onBackPressed()
        }
    }
}