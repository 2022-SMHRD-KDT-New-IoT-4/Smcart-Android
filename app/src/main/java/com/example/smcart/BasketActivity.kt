package com.example.smcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 장바구니 페이지
class BasketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        val rcBasketList = findViewById<RecyclerView>(R.id.rcBasketList)
        val btnSwitch = findViewById<Switch>(R.id.btnSwitch)
        val tvPay = findViewById<TextView>(R.id.tvPay)

        val data = ArrayList<BasketVO>()
        data.add(BasketVO("세척당근 3개입/봉", "3,600","2",R.drawable.carrot))
        data.add(BasketVO("대파 1kg /단", "4,200","1",R.drawable.greenonion))
        data.add(BasketVO("목우촌 국내산 돼지 앞다리 1000g/팩", "10,900","1",R.drawable.pork))
        data.add(BasketVO("미니뿌셔 불고기맛 55g 5개입/봉", "3,800","4",R.drawable.snack))
        data.add(BasketVO("긴 상품명 어쩌고 저쩌고 두 줄일 때 더 길게 더 길게 짱길게1kg /단", "980","1",R.drawable.greenonion))
        // please
        val adapter = BasketAdapter(applicationContext,data)
        rcBasketList.adapter = adapter
        rcBasketList.layoutManager = LinearLayoutManager(this)


        tvPay.setOnClickListener {
            val intent = Intent(this@BasketActivity,PayActivity::class.java)
            startActivity(intent)
        }


    }
}