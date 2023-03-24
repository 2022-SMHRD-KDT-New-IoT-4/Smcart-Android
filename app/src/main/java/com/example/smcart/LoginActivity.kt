package com.example.smcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val etLoginId = findViewById<EditText>(R.id.etLoginId)
        val etLoginPw = findViewById<EditText>(R.id.etLoginPw)

        queue = Volley.newRequestQueue(applicationContext)

        val url = "http://211.223.106.67:8081/cart/AndLogin.do"
        btnLogin.setOnClickListener {
            val stringRequest: StringRequest = object : StringRequest(
                Request.Method.POST,
                url,
                Response.Listener { response: String ->
                    Log.d("로그인결과값", response)
                    if(response != "null"){
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener { error: VolleyError ->
                    Log.d(
                        "안녕",
                        "That didn't work!" + error.message
                    )
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String>? {
                    val params: MutableMap<String, String> = HashMap()
                    params["user_id"] = etLoginId.text.toString()
                    params["user_pw"] = etLoginPw.text.toString()
                    return params
                }
            }
            queue.add(stringRequest)
        }



    }
}