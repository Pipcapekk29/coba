package com.example.tryfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tryfragment.databinding.ActivityRiwayatactiveBinding

class RiwayatactiveActivity : AppCompatActivity() {
    private lateinit var  recyclerView: RecyclerView
    private lateinit var dataUserlist : ArrayList<datamember>
    private lateinit var binding: ActivityRiwayatactiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayatactive)

        binding = ActivityRiwayatactiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backActivityRiwayat.setOnClickListener {
            onBackPressed()
        }

        recyclerView = findViewById(R.id.setiingfetc)
        dataUserlist = ArrayList<datamember>()

//        daya dummy
        val nama = arrayOf(
            R.drawable.img_17,
            R.drawable.img_16,
            R.drawable.img_17,
            R.drawable.img_16,
        )


        val pos = arrayOf(
            "23 september 2022",
            "24 september 2022",
            "23 september 2022",
            "24 september 2022"
        )
        val huruf = arrayOf(
            "INVOICE: 19278910391",
            "INVOICE: 8273918021",
            "INVOICE: 19278910391",
            "INVOICE: 8273918021"
        )
        val pertanyaan = arrayOf(
            "Total Bayar: 39.000",
            "Total Bayar: 30.000",
            "Total Bayar: 39.000",
            "Total Bayar: 30.000"
        )

        for (i in nama.indices){
            dataUserlist.add(
                datamember(
                    nama[i],

                )
            )
            populateData()
        }
    }

    private fun populateData(){
        val linearManager = LinearLayoutManager(this)
        linearManager.reverseLayout = true
        linearManager.stackFromEnd = true
        recyclerView.layoutManager = linearManager
        val adp = adapmember(this,dataUserlist)
        recyclerView.adapter = adp
    }
}
