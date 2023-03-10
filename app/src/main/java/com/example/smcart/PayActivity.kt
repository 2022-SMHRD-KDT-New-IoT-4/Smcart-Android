package com.example.smcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class PayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)


        val imgBarcode = findViewById<ImageView>(R.id.imgBarcode)



        imgBarcode.setOnClickListener {
            val intent = Intent(this@PayActivity,PayDoneActivity::class.java)
            startActivity(intent)
        }
    }
}