package com.example.smcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val etLoginId = findViewById<EditText>(R.id.etLoginId)
        val etLoginPw = findViewById<EditText>(R.id.etLoginPw)

        btnLogin.setOnClickListener {

            var id = etLoginId.text.toString()
            var pw = etLoginPw.text.toString()

            // db에서 조회하는 로직으로 변경하기
            if(id == "123" && pw == "123" ){
                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this@LoginActivity,
                    "로그인 정보가 일치하지 않습니다.",Toast.LENGTH_SHORT).show()
            }


        }



    }
}