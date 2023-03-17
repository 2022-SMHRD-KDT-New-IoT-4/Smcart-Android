package com.example.smcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PayHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_history)

        val rcPayHistory = findViewById<RecyclerView>(R.id.rcPayHistory)

        val data = ArrayList<PayHistoryVO>()
        // total에 db 값 넣기
        data.add(PayHistoryVO("세척당근 3개입/봉", "12.34 (일)", 50, R.drawable.card01, 123456))
        data.add(PayHistoryVO("세척당근 100개입/봉", "07.99 (화)", 134, R.drawable.card02, 78945651))
        data.add(PayHistoryVO("세척당근 1000개입/봉", "01.68 (목)", 1987, R.drawable.card3, 1546123896))


        val adapter = PayHistoryAdapter(applicationContext,data)
        rcPayHistory.adapter = adapter
        rcPayHistory.layoutManager = LinearLayoutManager(this)


    }
}