package com.geofferyj.gadsleaderboard

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_item.view.*


class RVAdapter(val userInfo: List<UserInfo>, val image: Int) :
    RecyclerView.Adapter<RVAdapter.RVViewHolder>() {

    inner class RVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return RVViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        val p = userInfo[position]
        holder.itemView.apply {
            card_image.setImageResource(image)
            card_text_heading.text = userInfo[position].name
            card_text_body.text =
                if (p.hours != null) "${p.hours} learning hours, ${p.country}" else "${p.score} skill IQ Score, ${p.country}"

        }
        Log.d("RecyclerJoe", holder.itemView.card_text_body.text.toString())
    }

    override fun getItemCount(): Int {
        return userInfo.size
    }
}
