package com.example.smcart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BasketAdapter(var context : Context, var data : ArrayList<BasketVO>):
RecyclerView.Adapter<BasketAdapter.ViewHolder>(){
    
    //테스트
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvProdName : TextView
        val tvProdPrice : TextView
        val tvProdCnt : TextView
        val imgProd : ImageView


        init {
            tvProdName = view.findViewById(R.id.tvProdName)
            tvProdPrice = view.findViewById(R.id.tvProdPrice)
            tvProdCnt = view.findViewById(R.id.tvProdCnt)
            imgProd = view.findViewById(R.id.imgProd)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.basket_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketAdapter.ViewHolder, position: Int) {
        holder.tvProdName.text = data[position].name
        holder.tvProdPrice.text = data[position].price
        holder.tvProdCnt.text = data[position].cnt
        holder.imgProd.setImageResource(R.drawable.carrot)
    }

    override fun getItemCount(): Int {
        return  data.size
    }
}