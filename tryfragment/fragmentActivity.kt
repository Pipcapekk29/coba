package com.example.tryfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tryfragment.databinding.ActivityFragmentBinding

class fragmentActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(homeFragment())

        binding.frag.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homefragment -> replaceFragment(homeFragment())
                R.id.promo -> replaceFragment(Fragment_promo())
                R.id.mymember -> replaceFragment(fragmenMember())
                R.id.setting -> replaceFragment(setiingfragment())

                else->{

                }
            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragacty, fragment)
        fragmentTransaction.commit()

    }
}

