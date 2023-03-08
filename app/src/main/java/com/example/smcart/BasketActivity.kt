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
        data.add(BasketVO("세척당근 3개입/봉", "3,600","2",R.drawable.carrot))
        data.add(BasketVO("대파 1kg /단", "4,200","1",R.drawable.greenonion))
        data.add(BasketVO("대파 1kg /단", "4,200","1",R.drawable.greenonion))
        data.add(BasketVO("대파 1kg /단", "4,200","1",R.drawable.greenonion))
        data.add(BasketVO("긴 상품명 어쩌고 저쩌고 두 줄일 때 더 길게 더 길게 짱길게1kg /단", "4,200","1",R.drawable.greenonion))
        // 윤한아 땡겨줘

        val adapter = BasketAdapter(applicationContext,data)
        rcBasketList.adapter = adapter
        rcBasketList.layoutManager = LinearLayoutManager(this)



    }
}