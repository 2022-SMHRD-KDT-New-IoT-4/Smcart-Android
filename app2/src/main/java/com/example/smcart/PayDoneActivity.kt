package com.example.smcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PayDoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_done)

        val btnGoShop = findViewById<Button>(R.id.btnGoShop)
        val btnPayDetail = findViewById<Button>(R.id.btnPayDetail)


        btnGoShop.setOnClickListener {
            val intent = Intent(this@PayDoneActivity,MainActivity::class.java)
            startActivity(intent)
        }

        btnPayDetail.setOnClickListener {
            val intent = Intent(this,PayDetailActivity::class.java)
            startActivity(intent)
        }




    }
}