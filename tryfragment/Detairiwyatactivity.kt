package com.example.tryfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tryfragment.databinding.ActivityDetairiwyatactivityBinding

class Detairiwyatactivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetairiwyatactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detairiwyatactivity)


        binding = ActivityDetairiwyatactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.balikriwayatfetai.setOnClickListener {
            onBackPressed()
        }
    }
}