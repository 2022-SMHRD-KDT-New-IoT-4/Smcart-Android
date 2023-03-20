package com.example.smcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.InvocationTargetException

// 장바구니 페이지
class BasketActivity : AppCompatActivity() {
    private lateinit var queue: RequestQueue
    private lateinit var request : StringRequest
    private lateinit var barcodenum : String
    private lateinit var rcBasketList : RecyclerView

    private val rvInterfaceInstance: CallbackInterface = object :CallbackInterface{
        override fun onClick(view: View) {
            val index : Int = rcBasketList.getChildAdapterPosition(view)
            Log.d("ind", index.toString())

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        barcodenum = "8801062883646"

        rcBasketList = findViewById<RecyclerView>(R.id.rcBasketList)


        val btnSwitch = findViewById<Switch>(R.id.btnSwitch)
        val tvPay = findViewById<TextView>(R.id.tvPay)


        queue  = Volley.newRequestQueue(this)
        var url = "http://211.223.106.67:8081/cart/Product.do"


        val data = ArrayList<BasketVO>()
        // 문제 1) 장바구니 테이블이 아닌 상품정보테이블에서 가져오는 중?
        request = object : StringRequest(
            Method.POST, url,
            Response.Listener<String> { response ->
                Log.d("결과", response.toString())
                val jsonArray = JSONArray(response)
                for(i in 0..jsonArray.length()) {
                    var name = jsonArray.getJSONObject(i).getString("prod_name")
                    var price = jsonArray.getJSONObject(i).getInt("prod_price")
                    var cnt = "1"
                    var img = jsonArray.getJSONObject(i).getString("prod_img")
                    data.add(BasketVO(name, price,cnt.toInt() , img)).toString()
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



//        data.add(BasketVO("세척당근 3개입/봉", "3,600","2",R.drawable.carrot))
//        data.add(BasketVO("대파 1kg /단", "4,200","1",R.drawable.greenonion))
//        data.add(BasketVO("목우촌 국내산 돼지 앞다리 1000g/팩", "10,900","1",R.drawable.pork))
//        data.add(BasketVO("미니뿌셔 불고기맛 55g 5개입/봉", "3,800","4",R.drawable.snack))
//        data.add(BasketVO("긴 상품명 어쩌고 저쩌고 두 줄일 때 더 길게 더 길게 짱길게1kg /단", "980","1",R.drawable.greenonion))
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