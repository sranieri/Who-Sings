package com.sample.whosings.ui.chart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.whosings.R
import com.sample.whosings.databinding.ViewholderChartItemBinding
import com.sample.whosings.db.model.User


class ChartAdapter(private val context: Context, var users: List<User>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ViewholderChartItemBinding.inflate(LayoutInflater.from(context))
        return ChartViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ChartViewHolder){
            val user = users[position]
            val chartPosition = position + 1
            holder.chartNumber.text = "$chartPosition"
            holder.username.text = user.username
            holder.score.text = "${user.score}"
            when (chartPosition) {
                1 -> {
                    holder.achievementImage.setImageResource(R.drawable.ic_achievement_gold)
                }
                2 -> {
                    holder.achievementImage.setImageResource(R.drawable.ic_achievement_silver)
                }
                3 -> {
                    holder.achievementImage.setImageResource(R.drawable.ic_achievement_bronze)
                }
            }
            if(position == itemCount - 1){
                holder.bottomSeparator.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }


    internal class ChartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username : TextView
        val score : TextView
        val chartNumber: TextView
        val achievementImage : ImageView
        val bottomSeparator: View

        init {
            username = itemView.findViewById(R.id.username)
            chartNumber = itemView.findViewById(R.id.chart_number)
            score = itemView.findViewById(R.id.score)
            achievementImage = itemView.findViewById(R.id.img_achievement)
            bottomSeparator = itemView.findViewById(R.id.bottom_separator)
        }
    }
}