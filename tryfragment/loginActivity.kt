package com.example.tryfragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONException
import org.json.JSONObject

class loginActivity : AppCompatActivity() {
    private lateinit var btn : Button
    private lateinit var daftar : TextView
    private lateinit var nama : EditText
    private lateinit var pass : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        nama = findViewById(R.id.editlogin)
        pass = findViewById(R.id.editpasslogin)

        btn = findViewById(R.id.btnlogin)
//        btn.setOnClickListener {
//            val intent = Intent(this, fragmentActivity::class.java)
//            startActivity(intent)
//            Toast.makeText(this,"Berhasil Login", Toast.LENGTH_SHORT).show()
//        }

        daftar = findViewById(R.id.btndaftar_login)
        btn.setOnClickListener {
            clickLogin()
        }
    }

    fun clickLogin(){
            if(nama.text.toString().trim().isEmpty()) {
                nama.error = "Email Harus Di Isi!"
            }else if (pass.text.toString().trim().isEmpty()){
                pass.error ="Password Harus Di Isi!"
            }else{
                if (pass.text.trim().length<8){
                    pass.error ="Password 8 character"
                }
                else{
                    Toast.makeText(this,"Registrasi Berhasil!", Toast.LENGTH_LONG).show()
                }

            }
            val jsonObject = JSONObject()
            try {
                jsonObject.put("email", nama.text.toString().trim())
                jsonObject.put("password",pass.text.toString().trim())

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.post("https://grocery-api.tonsu.site/auth/login")
                .addJSONObjectBody(jsonObject) // posting json
                .setTag("test")
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        // do anything with response
                        try {
                            Log.d("ini respon",response.toString())
                            if (response?.getString("success").equals("true")){
                                val getmessage = response?.getString("message")
                                Toast.makeText(this@loginActivity,getmessage, Toast.LENGTH_SHORT).show()


                                //get token
                                val getToken = response?.getString("token")
                                val preferenShare = getSharedPreferences("GET_TOKEN", Context.MODE_PRIVATE)
                                val editor = preferenShare.edit()
                                editor.putString("token",getToken)
                                editor.commit()
                                val intent= Intent(this@loginActivity,fragmentActivity::class.java)
                                startActivity(intent)
                            }

                        }catch (e: JSONException){
                            Toast.makeText(this@loginActivity,e.toString(), Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onError(error: ANError) {
                        Log.d("onError", error.toString())
                    }
                })
        }





}