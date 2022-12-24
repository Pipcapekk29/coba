package com.example.tryfragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splass)

        supportActionBar?.hide()

        Handler().postDelayed({
                              val intent = Intent(this@splass,loginActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}