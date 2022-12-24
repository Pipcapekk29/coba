package com.example.tryfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tryfragment.databinding.ActivityTetntangkamiBinding

class TetntangkamiActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTetntangkamiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTetntangkamiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tntng.setOnClickListener {
            onBackPressed()
        }
    }
}