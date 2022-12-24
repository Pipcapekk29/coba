package com.example.tryfragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.renderscript.RenderScript.Priority
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONException
import org.json.JSONObject


class DaftarActivity : AppCompatActivity() {
    private lateinit var inputName : EditText
    private lateinit var inputEmail : EditText
    private lateinit var inputBox : CheckBox
    private lateinit var inputPas : EditText
    private lateinit var btn : Button
    private lateinit var login : TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)

        btn = findViewById(R.id.btndaftar)
        inputName = findViewById(R.id.editname)
        inputEmail = findViewById(R.id.editemail)
        inputPas = findViewById(R.id.editpass)
        inputBox = findViewById(R.id.box)
        btn.setOnClickListener{

            if (inputName.text.toString().trim().isEmpty()){
                inputName.error = "nim kosong"
                inputName.requestFocus()
            }
            else if (inputEmail.text.toString().trim().isEmpty()){
                inputEmail.error = "password kosong"
                inputEmail.requestFocus()

            }
            else if (inputPas.text.trim().length<8){
                inputPas.error ="Password 8 character"
                inputPas.requestFocus()
            }
            else if (!inputBox.isChecked) {
                inputBox.error = "Wajib centang persetujuan"
                inputBox.requestFocus()
            }
            else {
                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_LONG).show()
            }

                val cek = inputBox.isChecked
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("name", inputName.text.toString().trim())
                    jsonObject.put("email",inputEmail.text.toString().trim())
                    jsonObject.put("password",inputPas.text.toString().trim())
                    jsonObject.put("terms",if (cek) 1 else 0)
                } catch (e : JSONException) {
                    e.printStackTrace()
                }

                AndroidNetworking.post("https://grocery-api.tonsu.site/auth/register")
                    .addJSONObjectBody(jsonObject) // posting json
                    .addHeaders("Content-Type", "application/json")
                    .setPriority(com.androidnetworking.common.Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject) {
                            // do anything with response

                            Log.d("ini cek responsi",response.toString())
                            try {
                                if(response.getString("success").equals("true")){
                                    Toast.makeText(this@DaftarActivity, response.getString("message"), Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@DaftarActivity, loginActivity::class.java)
                                    startActivity(intent)
                                }

                            }catch (e: JSONException){
                                Toast.makeText(this@DaftarActivity,e.toString(),Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onError(error: ANError?) {
                            Log.d("onError", error.toString())
                        }
                    })
            }


//        btn.setOnClickListener {
//            val intent = Intent(this, loginActivity::class.java)
//            startActivity(intent)
//
//        }



        login = findViewById(R.id.btnlogin_daftar)
        login.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }

    }




}