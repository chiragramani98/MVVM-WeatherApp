package com.projects.weatherdrop.adapter

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.projects.weatherdrop.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext

@BindingAdapter("app:setWeatherIcon")
fun setWeatherIcon(view: ImageView, iconPath: String?){
    if (iconPath.isNullOrEmpty()){
        return
    }
    Picasso.get().cancelRequest(view)
    val newPath = iconPath.replace(iconPath, "_$iconPath")
    val imageId = view.context.resources.getIdentifier(newPath, "drawable", view.context.packageName)
    val imageDrawable = ResourcesCompat.getDrawable(view.context.resources, imageId, null)
    view.setImageDrawable(imageDrawable)
}
