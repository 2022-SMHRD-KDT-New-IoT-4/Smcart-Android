package com.example.smcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Handler
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


// import kotlinx.android.synthetic.main.activity_qractivity.*

// lateinit 초기화 지연
// request 객체를 서버로 요청보내는 역할
lateinit var queue: RequestQueue
// 요청과 응답에 대한 로직(기능)을 담고있는 객체
lateinit var request: StringRequest
lateinit var result : IntentResult
lateinit var webView: WebView
class QRActivity : AppCompatActivity() {

    // private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // 화면이 세로모드에서 가로모드로 전환 시 onCreate함수가 다시 호출된다.
        // 만약 전역변수를 설정하고 그 값을 유지하며 항상 사용해야 하는 경우라도
        // 화면이 세로모드에서 가로모드로 변경될 경우 전역변수에 설정한 값이 모두 초기화 된다.
        // 이런 경우 변경된 값을 유지하고 싶다면  savedInstanceState을 이용하는 것이 좋다.

        setContentView(R.layout.activity_qractivity)

        val btnScan = findViewById<Button>(R.id.btnScan)

        // QR 코드 버튼 이벤트
        btnScan.setOnClickListener {
            qrStart()
            Handler().postDelayed({
//                requestSend("1")
            },5000)
        }




    }

    fun qrStart(){
        val integrator = IntentIntegrator(this)
 //       integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE) // 여러가지 바코드중에 특정 바코드 설정 가능
        integrator.setPrompt("모듈의 QR 코드를 스캔해 주세요") // 스캔할 때 하단의 문구
 //       integrator.setCameraId(0) // 0은 후면 카메라, 1은 전면 카메라
        integrator.setBeepEnabled(true) // 바코드를 인식했을 때 삑 소리유무
 //       integrator.setBarcodeImageEnabled(true) // 스캔 했을 때 스캔한 이미지 사용여부
        integrator.setOrientationLocked(true)
        integrator.initiateScan() // 스캔 시작
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        // QR 코드를 찍은 결과를 변수에 담는다.
        result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        //결과가 있으면
        if (result != null) {
            // 컨텐츠가 없으면
            if (result.contents == null) {
                //토스트를 띄운다.
                Toast.makeText(this, "다시 시도해 주세요.", Toast.LENGTH_LONG).show()
            }
            // 컨텐츠가 있으면
            else {
                //토스트를 띄운다.
                Toast.makeText(this, "연동이 완료되었습니다." + result.contents, Toast.LENGTH_LONG).show()
                Log.d("TTT", "QR 코드 URL:${result.contents}")

                //서버에 큐알코드 전송하기
                requestSend(result.contents.toString())
//                Log.d("들어가니?",result.contents.toString())

            }
            // 결과가 없으면
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    // 서버에 요청 보내기
    private fun requestSend(value:String) {

        queue = Volley.newRequestQueue(applicationContext)

        val url = "http://211.223.106.67:8081/cart/Qr.do"



        val stringRequest: StringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response: String ->

//                var row = response.toInt() // row는 서버에서 받아온 조회 결과값.
                var result : String?
                result = response // row는 서버에서 받아온 조회 결과값.
                if (result != null){
                    Log.d("서버에서 받아온 값!",response)
                    // 서버로 넘긴 module_qr정보를 db에서 조회 후,
                    // 받아온 row값이 1일 경우 모듈 연동 성공 + 장바구니 화면으로 넘기기
                    val row = result.toInt()
                    if(row>0){
                        Toast.makeText(this, "즐거운 쇼핑 되세요.",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@QRActivity, BasketActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"다시 시도해 주세요.",Toast.LENGTH_SHORT).show()
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


}