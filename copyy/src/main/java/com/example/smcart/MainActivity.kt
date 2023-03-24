package com.example.smcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStartShopping = findViewById<Button>(R.id.btnStartShopping)


        btnStartShopping.setOnClickListener {
            val intent = Intent(this@MainActivity,QRActivity::class.java)
            startActivity(intent)
        }









    }
}