package com.example.smcart

import android.R.attr.tag
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult


// import kotlinx.android.synthetic.main.activity_qractivity.*

    // lateinit 초기화 지연
    // request 객체를 서버로 요청보내는 역할
    lateinit var queue: RequestQueue
    // 요청과 응답에 대한 로직(기능)을 담고있는 객체
    lateinit var request:StringRequest
    lateinit var result : IntentResult
    lateinit var webView: WebView
class QRActivity : AppCompatActivity() {
    // private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qractivity)
        // binding = ActivityMainBinding.inflate(layoutInflater)
        // val view = binding.root
        // setContentView(webView)
        val btnScan = findViewById<Button>(R.id.btnScan)


        // QR 코드 버튼 이벤트
        btnScan.setOnClickListener {
            qrStart()
            Handler().postDelayed({
                requestSend("1")
            },5000)

        }
    }
    fun qrStart(){

        val integrator = IntentIntegrator(this)

        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE) // 여러가지 바코드중에 특정 바코드 설정 가능
        integrator.setOrientationLocked(true);
        integrator.setPrompt("QR 코드를 스캔하여 주세요") // 스캔할 때 하단의 문구
        integrator.setCameraId(0) // 0은 후면 카메라, 1은 전면 카메라
        integrator.setBeepEnabled(true) // 바코드를 인식했을 때 삑 소리유무
        integrator.setBarcodeImageEnabled(false) // 스캔 했을 때 스캔한 이미지 사용여부
        integrator.initiateScan() // 스캔

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        // QR 코드를 찍은 결과를 변수에 담는다.
        result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        Log.d("TTT", "QR 코드 체크")


        //결과가 있으면
        if (result != null) {
            // 컨텐츠가 없으면
            if (result.contents == null) {
                //토스트를 띄운다.
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            }
            // 컨텐츠가 있으면
            else {
                //토스트를 띄운다.
                Toast.makeText(this, "scanned" + result.contents, Toast.LENGTH_LONG).show()
                Log.d("TTT", "QR 코드 URL:${result.contents}")

                //서버에 큐알코드 전송하기
                requestSend(result.contents.toString())
                Log.d("들어가니?",result.contents.toString())

                //웹뷰 설정
                // wvQr.settings.javaScriptEnabled = true
                // wvQr.webViewClient = WebViewClient()

                //웹뷰를 띄운다.
                // wvQr.loadUrl(result.contents)
            }
            // 결과가 없으면
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun requestSend(value:String) {

        queue = Volley.newRequestQueue(applicationContext)

        val url = "http://192.168.35.93:8081/cart/Main.do"

        val stringRequest: StringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener { response: String ->
                Log.d(
                    "안녕",
                    "Response is : ${result.contents}"
                )
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
                params["qr_code"] = value
                return params
            }


        }
        queue.add(stringRequest)
    }


}