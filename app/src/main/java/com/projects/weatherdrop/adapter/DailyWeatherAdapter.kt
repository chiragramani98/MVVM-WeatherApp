package com.projects.weatherdrop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.projects.weatherdrop.data.model.Daily
import com.projects.weatherdrop.databinding.DailyWeatherListBinding

class DailyWeatherAdapter: ListAdapter<Daily, DailyWeatherAdapter.DailyViewHolder>(differCallBack()) {

    class DailyViewHolder(val binding: DailyWeatherListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Daily){
            binding.dailyData = item
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): DailyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DailyWeatherListBinding.inflate(layoutInflater, parent, false)
                return DailyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        return DailyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class differCallBack : DiffUtil.ItemCallback<Daily>(){
        override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
            return oldItem == newItem
        }
    }
}