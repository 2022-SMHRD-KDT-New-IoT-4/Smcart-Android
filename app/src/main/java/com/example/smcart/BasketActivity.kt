package com.example.smcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 장바구니 페이지
class BasketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        val rcBasketList = findViewById<RecyclerView>(R.id.rcBasketList)


        val data = ArrayList<BasketVO>()
        data.add(BasketVO("세척당근 3개입", "3,600","2",R.drawable.carrot))


        val adapter = BasketAdapter(applicationContext,data)
        rcBasketList.adapter = adapter
        rcBasketList.layoutManager = LinearLayoutManager(this)



    }
}