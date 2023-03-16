package com.example.smcart

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

var total = 0
class PayHistoryAdapter(var context : Context, var data : ArrayList<PayHistoryVO>):
    RecyclerView.Adapter<PayHistoryAdapter.ViewHolder>(){


    //테스트
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvDate : TextView
        val tvList : TextView
        val tvTotal : TextView
        val tvProdLen : TextView
        val ivPayMethod : ImageView


        init {
            tvDate = view.findViewById(R.id.tvDate)
            tvList = view.findViewById(R.id.tvList)
            tvTotal = view.findViewById(R.id.tvTotal)
            tvProdLen = view.findViewById(R.id.tvProdLen)
            ivPayMethod = view.findViewById(R.id.ivPayMethod)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayHistoryAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.payhistory_list,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: PayHistoryAdapter.ViewHolder, position: Int) {

        // button OnClickListener 가능^^
        holder.tvDate.text = data[position].date
        // holder.tvPrice.text = data[position].price
        // total = data[position].price.toInt() + total
        Log.d("뭘까?",total.toString())
        // name 대표명으로 수정하기
        holder.tvList.text = data[position].flagship
        holder.tvProdLen.text = data[position].prodlen.toString()
        holder.tvTotal.text = data[position].total.toString()
        holder.ivPayMethod.setImageResource(data[position].paymethod)


    }


    override fun getItemCount(): Int {
        return  data.size
    }
}