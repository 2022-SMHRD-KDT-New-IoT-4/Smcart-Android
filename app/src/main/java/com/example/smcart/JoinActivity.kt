package com.example.smcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class JoinActivity : AppCompatActivity() {
    private lateinit var queue: RequestQueue
    private lateinit var request : StringRequest
    private lateinit var barcodenum : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val etJoinid = findViewById<EditText>(R.id.etJoinId)
        val etJoinPw = findViewById<EditText>(R.id.etJoinPw)
        val etJoinNickname = findViewById<EditText>(R.id.etJoinNickname)
        val etJoinPhone = findViewById<EditText>(R.id.etJoinPhone)
        val etJoinName = findViewById<EditText>(R.id.etJoinName)
        val btnJoin = findViewById<Button>(R.id.btnJoin)

        queue = Volley.newRequestQueue(applicationContext)

        // AndJoin.do로 들어감
        val url = "http://211.223.106.67:8081/cart/AndJoin.do"

        btnJoin.setOnClickListener {
            val stringRequest: StringRequest = object : StringRequest(
                Request.Method.POST,
                url,
                Response.Listener { response: String ->
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
                    // 보내주는 값
                    params["user_id"] = etJoinid.text.toString()
                    params["user_pw"] = etJoinPw.text.toString()
                    params["user_name"] = etJoinName.text.toString()
                    params["user_nick"] = etJoinNickname.text.toString()
                    params["user_phone"] = etJoinPhone.text.toString()
                    return params
                }
            }
            queue.add(stringRequest)
        }



    }

}
