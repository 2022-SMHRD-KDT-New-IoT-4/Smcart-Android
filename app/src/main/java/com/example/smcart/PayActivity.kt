package com.example.smcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2

class PayActivity : AppCompatActivity() {

    lateinit var vpcard : ViewPager2
    lateinit var textview11 : TextView

    private val imageList = mutableListOf<Int>().apply {
        add(R.drawable.card01)
        add(R.drawable.card02)
        add(R.drawable.card3)

    }

    private val textList = mutableListOf<String>().apply {
        add("신한카드")
        add("11번가")
        add("비자카드")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)
        textview11 = findViewById(R.id.textView11)
        vpcard = findViewById(R.id.vpcard)



        val imgBarcode = findViewById<ImageView>(R.id.imgBarcode)



        imgBarcode.setOnClickListener {
            val intent = Intent(this@PayActivity,PayDoneActivity::class.java)
            startActivity(intent)
        }

        mainInitViewPager2()
    }
    private fun mainInitViewPager2(){
        textview11.text = "신한카드"
        vpcard.apply {
            clipToPadding= false
            clipChildren= false
            offscreenPageLimit = 1
            adapter = CardAdepter(this@PayActivity, imageList)
        }
        vpcard.setPageTransformer(MarginPageTransformer(100))
        vpcard.setPadding(200,0,200,0)
        mainViewChangeEvent()
    }

    private fun mainViewChangeEvent(){
        vpcard.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                textview11.text = textList[position]
            }
        })
    }

}