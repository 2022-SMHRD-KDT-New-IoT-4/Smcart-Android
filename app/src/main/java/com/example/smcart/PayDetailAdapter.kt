package com.example.smcart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// 결제 상세 내역
class PayDetailAdapter (var context : Context, var data : ArrayList<BasketVO>):
    RecyclerView.Adapter<PayDetailAdapter.ViewHolder>(){

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvProdNamePD : TextView
        val tvProdPricePD : TextView
        val tvProdCntPD : TextView
        val imgProdPD : ImageView

        init {
            tvProdNamePD = view.findViewById(R.id.tvProdNamePD)
            tvProdPricePD = view.findViewById(R.id.tvProdPricePD)
            tvProdCntPD = view.findViewById(R.id.tvProdCntPD)
            imgProdPD = view.findViewById(R.id.imgProdPD)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayDetailAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.paydetail_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PayDetailAdapter.ViewHolder, position: Int) {
        holder.tvProdNamePD.text = data[position].name
        holder.tvProdPricePD.text = data[position].price
        holder.tvProdCntPD.text = data[position].cnt
        holder.imgProdPD.setImageResource(R.drawable.carrot)

    }


}