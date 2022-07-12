package com.projects.weatherdrop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.projects.weatherdrop.data.model.Hourly
import com.projects.weatherdrop.databinding.HourlyWeatherListBinding

class HourlyWeatherAdapter: ListAdapter<Hourly, HourlyWeatherAdapter.HourlyViewHolder>(differCallBack()) {

    class HourlyViewHolder(val binding: HourlyWeatherListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Hourly){
            binding.hourlyData = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HourlyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HourlyWeatherListBinding.inflate(layoutInflater, parent, false)
                return HourlyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        return HourlyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class differCallBack : DiffUtil.ItemCallback<Hourly>(){
        override fun areItemsTheSame(oldItem: Hourly, newItem: Hourly): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: Hourly, newItem: Hourly): Boolean {
            return oldItem == newItem
        }
    }
}