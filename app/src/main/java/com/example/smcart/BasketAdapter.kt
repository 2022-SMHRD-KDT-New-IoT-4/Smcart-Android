package com.example.smcart

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.zxing.integration.android.IntentResult
import org.json.JSONArray
import org.json.JSONObject

class BasketAdapter(var context : Context, var data : ArrayList<BasketVO>):
    RecyclerView.Adapter<BasketAdapter.ViewHolder>(){

    // lateinit 초기화 지연
    // request 객체를 서버로 요청보내는 역할
    lateinit var queue: RequestQueue
    // 요청과 응답에 대한 로직(기능)을 담고있는 객체
    lateinit var request: StringRequest
    lateinit var result : IntentResult
    lateinit var webView: WebView

    //테스트
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvProdName : TextView
        val tvProdPrice : TextView
        val tvProdCnt : TextView
        val imgProd : ImageView
        val imgPlus : ImageView
        val imgMinus :ImageView
        val btnHeart : ImageButton
        val btnTrashcan : ImageButton
        val btnProdCheck : CheckBox




        init {
            tvProdName = view.findViewById(R.id.tvProdName)
            tvProdCnt = view.findViewById(R.id.tvProdCnt)
            imgProd = view.findViewById(R.id.imgProd)
            imgPlus = view.findViewById(R.id.imgPlus)
            imgMinus = view.findViewById(R.id.imgMinus)
            btnHeart = view.findViewById(R.id.btnHeart)
            btnTrashcan = view.findViewById(R.id.btnTrashcan)
            btnProdCheck = view.findViewById(R.id.btnProdCheck)
            tvProdPrice = view.findViewById(R.id.tvProdPrice)


            imgPlus.setOnClickListener {
                var cnt = tvProdCnt.text.toString().toInt()  // 원래 개수
                val price = tvProdPrice.text.toString().toInt() / cnt // 원가
                cnt +=1 // 추가된 개수
                var cntp = requestSend(cnt.toString()).toString()
                Log.d("되니?",cntp)


                //tvProdCnt.text = cnt.toString() // + 1 개 된 개수 출력
                //tvProdPrice.text = (price * cnt).toString()

            }
            imgMinus.setOnClickListener {
                if(tvProdCnt.text.toString().toInt() != 1) {
                    var cnt = tvProdCnt.text.toString().toInt()  // 원래 개수
                    val price = tvProdPrice.text.toString().toInt() / cnt // 원가
                    cnt -=1 // 추가된 개수
                    requestSend(cnt.toString())
                    //tvProdCnt.text = cnt.toString() // + 1 개 된 개수 출력
                    //tvProdPrice.text = (price * cnt).toString()

                }
            }
            btnTrashcan.setOnClickListener {

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    data.removeAt(position)
                    notifyItemRemoved(position)


                }
            }
        }

    }

    private fun requestSend(value:String) {
        //queue = Volley.newRequestQueue(context)

        val url = "http://211.223.106.67:8081/cart/Basket.do"


        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.POST, url, null,
            Response.Listener<JSONArray> { response ->
                Log.d("안녕", response.toString())
                for (i in 0 until response.length()) {
                    val jsonObject = response[i] as JSONObject
                    val name = jsonObject.getString("name")
                    val price = jsonObject.getString("price")
                    val cnt = jsonObject.getString("cnt")
                    val img = jsonObject.getString("img")
                    Log.d("예호!", "$name, $price, $cnt, $img")
                    }
                },
        Response.ErrorListener { error -> Log.d("예호ㅠ", "error......$error") }
        )

        queue = Volley.newRequestQueue(context)
        queue.add(jsonArrayRequest)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.basket_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketAdapter.ViewHolder, position: Int) {
        holder.tvProdName.text = data[position].name
        holder.tvProdCnt.text = data[position].cnt
        val price =  data[position].price
        //.replace(",", "").toInt() * data[position].cnt.toInt()
        holder.tvProdPrice.text = price.toString()
        // 왜 안될까요? holder.imgProd.setImageResource(data[position].img.toString())
        holder.imgPlus.setImageResource(R.drawable.icon_plus)
        holder.imgMinus.setImageResource(R.drawable.icon_minus)
        holder.btnHeart.setImageResource(R.drawable.heart_gray)
        holder.btnTrashcan.setImageResource(R.drawable.trash_gray)


    }

    override fun getItemCount(): Int {
        return  data.size
    }
}