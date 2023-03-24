package com.example.smcart

// 장바구니와 결제 상세내역에 사용됩니다.
data class BasketVO(val cnt : Int, val img : String, val price : Int, val name : String) {
}