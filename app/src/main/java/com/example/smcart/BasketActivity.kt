package com.example.smcart

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray


// 장바구니 페이지
class BasketActivity : AppCompatActivity() {
    private lateinit var queue: RequestQueue
    private lateinit var request : StringRequest
//    private lateinit var barcodenum : String
    private lateinit var rcBasketList : RecyclerView

//    private val rvInterfaceInstance: CallbackInterface = object :CallbackInterface{
//        override fun onClick(view: View) {
//            val index : Int = rcBasketList.getChildAdapterPosition(view)
//            Log.d("ind", index.toString())
//        }
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        //barcodenum = "8801062883646"

        rcBasketList = findViewById<RecyclerView>(R.id.rcBasketList)
        val btnSwitch = findViewById<Switch>(R.id.btnSwitch)
        val tvPay = findViewById<TextView>(R.id.tvPay)
        val imageButton = findViewById<ImageView>(R.id.imageButton)
        val imageButton2 = findViewById<ImageView>(R.id.imageButton2)
        imageButton.setOnClickListener {
            val intent = Intent(this@BasketActivity, QRActivity::class.java)
            startActivity(intent)
        }
         imageButton2.setOnClickListener {
             val intent = Intent(this@BasketActivity, MainActivity::class.java)
             startActivity(intent)
         }
        queue  = Volley.newRequestQueue(this)
        var url = "http://220.95.45.227:8081/cart/Barcode2.do"

//        val t = Thread {
//            Looper.prepare()
//            val handler = Handler()
//            Looper.loop()
//        }
//        t.start()
        val t = HandlerThread("My Handler Thread")
        t.start()
        val handler = Handler(t.looper)

        val data = ArrayList<BasketVO>()


        request = object : StringRequest(
            Method.POST, url,
            Response.Listener<String> { response ->
                val jsonArray = JSONArray(response)
                Log.d("결과", response.toString())
                for(i in 0..jsonArray.length()-1) {
//                    var cnt = 1
                    var cnt = jsonArray.getJSONObject(i).getInt( "prod_cnt")
//                    var img = "R.drawable.carrot"
                    var img = jsonArray.getJSONObject(i).getString("prod_img")
                    var price = jsonArray.getJSONObject(i).getInt("prod_price")
//                    var price = 200
                    var name = jsonArray.getJSONObject(i).getString("prod_name")
                     data.add(BasketVO(cnt.toInt(), img,price.toInt(),name)).toString()
                    Log.d("data",data[i].toString())
                }

            },
            Response.ErrorListener { error ->
                Log.d("통신오류", error.printStackTrace().toString())
            })
        {
            override fun getBodyContentType(): String {

//                return  "application/json" // JSON 형태의 데이터를 보낼 예정이므로 application/json으로 설정합니다.
                return  "8886467105333" // 데이터 보내기? 모듈에서 서버로 바로 보내면 되는 것 아닌가?
            }

        }
        queue.add(request)


        // val cnt : Int, val img : String, val price : Int, val name : String
//        data.add(BasketVO(1,"R.drawable.carrot",3600,"세척당근 3개입/봉"))
//        data.add(BasketVO(1,"R.drawable.greenonion",4200,"대파 1kg /단",))
//        data.add(BasketVO(1,"R.drawable.pork", 10900,"목우촌 국내산 돼지 앞다리 1000g/팩"))
//        data.add(BasketVO(1, "R.drawable.snack",3800,"미니뿌셔 불고기맛 55g 5개입/봉"))
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