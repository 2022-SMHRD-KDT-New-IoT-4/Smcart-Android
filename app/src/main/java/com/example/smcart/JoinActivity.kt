package com.example.smcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONObject

class JoinActivity : AppCompatActivity() {
    lateinit var etJoinId : EditText
    lateinit var etJoinPw : EditText
    lateinit var etJoinNickName : EditText
    lateinit var etJoinPhone : EditText
    lateinit var etJoinName : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val btnJoin = findViewById<Button>(R.id.btnJoin)

        etJoinId = findViewById(R.id.etJoinId)
        etJoinPw = findViewById(R.id.etJoinPw)
        etJoinNickName = findViewById(R.id.etJoinNickName)
        etJoinPhone = findViewById(R.id.etJoinPhone)
        etJoinName = findViewById(R.id.etJoinName)




        // JoinVO로 묶음 - (서버로 보낼 값)
//        val input_data = JoinVO(id.toString(),pw.toString(),nickName.toString(),phone.toString(),name.toString())

        // 회원가입 버튼눌렀을 때 전송
        btnJoin.setOnClickListener {
            join()
        }


        }
    } // onCreate()끝


    //회원가입
    fun join(data : JoinVO){
        // 사용자가 입력한 값들
        val id = etJoinId.text
        val pw = etJoinPw.text
        val nickName = etJoinNickName.text
        val phone = etJoinPhone.text
        val name = etJoinName.text

        val url = "http://211.223.106.67:8081/cart/Join.do"
        // 웹 회원가입 페이지 그대로 사용해도 되는지 테스트해보자

        // 이 stringRequest를... (요청 정보 정의)
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            Response.Listener<String> {
                Log.d("서버응답 성공 : ", "server 응답 : $it")
            },
            Response.ErrorListener { error ->
                Log.d("서버응답 실패 : ", "error......$error")
            }) {
            // 서버가 요청하는 parameter 담기
            override  fun getParams() : MutableMap<String, String>{
                // Map : key, value 쌍 구조
                // Map : 불변, MutableMap: 가변적으로 데이터 변경
                val params = mutableMapOf<String,String>() // mutableMapOf : 빈 Map생성할 때 타입 명시
                params.put("id","$id") // put() : map에 데이터 삽입
                params.put("pw","$pw")
                params.put("nickName","$nickName")
                params.put("phone","$phone")
                params.put("name","$name")

                return  params
                // 전달할 데이터 담아서 반환하면 서버에 요청할 때 알아서 함께 전송해 줌
            }


        }
        val queue = Volley.newRequestQueue(this)
        queue.add(stringRequest)
    }


    // 서버에 요청 보내기
    private fun requestSend(value:String) {

        queue = Volley.newRequestQueue(applicationContext)

        val url = "http://211.223.106.67:8081/cart/Join.do"

        val stringRequest: StringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response: String ->

                var result : String?
                result = response // row는 서버에서 받아온 조회 결과값.
                if (result != null){
                    Log.d("서버에서 받아온 값!",response)
                    // 서버로 넘긴 module_qr정보를 db에서 조회 후,
                    // 받아온 row값이 1일 경우 모듈 연동 성공 + 장바구니 화면으로 넘기기
                    val row = result.toInt()
                    if(row>0){
                        Toast.makeText(this, "즐거운 쇼핑 되세요.", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@QRActivity, BasketActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
                    }
//                 0일 경우 연동 실패 토스트 띄우기

                }else{
                    Log.d("꽝!",response)
                }



//                Log.d(
//                    "안녕",
//                    "Response is : ${result.contents}"
//                )
            },
            Response.ErrorListener { error: VolleyError ->
                Log.d(
                    "안녕",
                    "That didn't work!" + error.message
                )
            }) {

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params["qr_code"] = value // value가 qr코드 값
                return params
            }


        }
        queue.add(stringRequest)
    }



} // class 끝