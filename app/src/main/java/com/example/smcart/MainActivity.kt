package com.example.smcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    lateinit var vpAd : ViewPager2

    private val imageList = mutableListOf<Int>().apply {
        add(R.drawable.ad1)
        add(R.drawable.ad2)
        add(R.drawable.ad3)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 초기화
        vpAd = findViewById<ViewPager2>(R.id.vpAd)
        val rcOften = findViewById<RecyclerView>(R.id.rcOften)
        val rcRelative = findViewById<RecyclerView>(R.id.rcRelative)
        val rcRec = findViewById<RecyclerView>(R.id.rcRec)

        // test data
        var data = ArrayList<RecomVO>()
        data.add(RecomVO("상품명상품명상품명상품명",5000,1,"R.drawable.carrot"))
        data.add(RecomVO("상품명",5000,1,"R.drawable.carrot"))
        data.add(RecomVO("te상품명상품명상품명상품명상품명st",5000,1,"R.drawable.carrot"))
        data.add(RecomVO("상품명상품명상품명",5000,1,"R.drawable.carrot"))

        // 오늘의 특가할인 RecyclerView
        val adapter = TodayAdapter(applicationContext, data)
        rcRec.adapter = adapter
        rcRec.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false); //세로정렬


        // 자주구매한 상품 RecyclerView
        val adapter2 = OftenAdapter(applicationContext,data)
        rcOften.adapter = adapter2
        rcOften.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false); //세로정렬

        // 연관상품 RecyclerView
        val adapter3 = RecentAdapter(applicationContext,data)
        rcRelative.adapter = adapter3
        rcRelative.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false); //세로정렬

        val btnMainStart = findViewById<Button>(R.id.btnMainStart)
        btnMainStart.setOnClickListener {
            val intent = Intent(this@MainActivity,QRActivity::class.java)
            startActivity(intent)

        }

        mainInitViewPager2()


    }private fun mainInitViewPager2(){
    vpAd.apply {
        clipToPadding= false
        clipChildren= false
        offscreenPageLimit = 1
        adapter = CardAdapter(this@MainActivity, imageList)
    }
//        vpAd.setPageTransformer(MarginPageTransformer(100))
//        vpAd.setPadding(200,0,200,0)
    mainViewChangeEvent()
}

private fun mainViewChangeEvent(){
    vpAd.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
        }
    })
}

}