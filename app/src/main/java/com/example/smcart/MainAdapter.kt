package com.example.smcart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
/**
 * RecyclerView Adapter 를 상속 받는다
 * 제네릭 타입으론 UI에 보여줄 View 를 담는 ViewHolder 이다.
 */

// Main의 Ad(ViewPager2)
class MainAdapter(private val context: Context, private val imageList: MutableList<Int> ): RecyclerView.Adapter<MainAdapter.PagerViewHolder>() {

    /**
     * View 를 담을 ViewHolder class 를 정의 한다.
     */
    inner class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val item: ImageView = itemView.findViewById(R.id.imgAd)
    }

    /**
     * ViewHolder 를 인스턴스화 하고 return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.cardlist,
            parent,
            false
        )
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return PagerViewHolder(view)
    }

    /**
     * 뷰와 데이터를 바인딩 하는 메서드
     */
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.item.setImageDrawable(ContextCompat.getDrawable(context, imageList[position]))
    }

    override fun getItemCount() = imageList.size

}