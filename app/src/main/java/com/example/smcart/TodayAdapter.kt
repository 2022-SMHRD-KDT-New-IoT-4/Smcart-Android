package com.example.smcart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TodayAdapter(var context : Context, var data : ArrayList<RecomVO>):
    RecyclerView.Adapter<TodayAdapter.ViewHolder>(){

    //테스트
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        // 선언
        var tvMainRecName: TextView
        var tvMainRecPrice : TextView
        var imgMainRecImg : ImageView
        // 초기화
        init {
            tvMainRecName = view.findViewById(R.id.tvMainRecName)
            tvMainRecPrice = view.findViewById(R.id.tvMainRecPrice)
            imgMainRecImg = view.findViewById(R.id.imgMainRecImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.recom_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodayAdapter.ViewHolder, position: Int) {
        holder.tvMainRecName.text = data[position].name
        holder.tvMainRecPrice.text = data[position].price.toString()
        holder.imgMainRecImg.setImageResource(R.drawable.carrot)



    }

    override fun getItemCount(): Int {
        return  data.size
    }
}