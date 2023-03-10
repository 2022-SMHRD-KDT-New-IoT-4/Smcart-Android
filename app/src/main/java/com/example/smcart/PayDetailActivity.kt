package com.example.smcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 결제 상세 내역
class PayDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_detail)

        val rcPayDetailList = findViewById<RecyclerView>(R.id.rcPayDetailList)


        val data = ArrayList<BasketVO>()
        data.add(BasketVO("세척당근 3개입/봉", "3,600","2",R.drawable.carrot))
        data.add(BasketVO("대파 1kg /단", "4,200","1",R.drawable.greenonion))
        data.add(BasketVO("대파 1kg /단", "4,200","1",R.drawable.greenonion))
        data.add(BasketVO("대파 1kg /단", "4,200","1",R.drawable.greenonion))
        data.add(BasketVO("긴 상품명 어쩌고 저쩌고 두 줄일 때 더 길게 더 길게 짱길게1kg /단", "4,200","1",R.drawable.greenonion))
        // please
        val adapter = PayDetailAdapter(applicationContext,data)
        rcPayDetailList.adapter = adapter
        rcPayDetailList.layoutManager = LinearLayoutManager(this)




    }
}