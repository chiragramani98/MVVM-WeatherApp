package com.projects.weatherdrop.data.model

import android.graphics.Color
import com.projects.weatherdrop.R
import java.text.SimpleDateFormat
import java.util.*

data class WeatherResponse(
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val minutely: List<Minutely>,
    val timezone: String,
    val timezone_offset: Int
) {
    fun getDateAndTime(t: Int): String? {
        val sdf = SimpleDateFormat("dd MMMM, hh:mm a")
        sdf.timeZone = TimeZone.getTimeZone(timezone)
        val formattedDate = sdf.format(Date(t.toLong() * 1000))

        return formattedDate
    }

    fun getSunrise(): String? {
        return getDateAndTime(current.sunrise)?.substringAfter(",")
    }

    fun getSunset(): String? {
        return getDateAndTime(current.sunset)?.substringAfter(",")
    }

    fun getBackgroundColor(): Int {
        return when(current.getWeatherItem().icon){
            "01d"-> Color.parseColor("#FF9D6F")
            "01n"-> Color.parseColor("#95608E")
            "02d"-> Color.parseColor("#454A4C")
            "02n"-> Color.parseColor("#454A4C")
            "03d"-> Color.parseColor("#454A4C")
            "03n"-> Color.parseColor("#454A4C")
            "04d"-> Color.parseColor("#454A4C")
            "04n"-> Color.parseColor("#454A4C")
            "09d"-> Color.parseColor("#0E1A26")
            "09n"-> Color.parseColor("#0E1A26")
            "10d"-> Color.parseColor("#0E8EFF")
            "10n"-> Color.parseColor("#0E8EFF")
            "11d"-> Color.parseColor("#124295")
            "11n"-> Color.parseColor("#124295")
            "13d"-> Color.parseColor("#ABAFBB")
            "13n"-> Color.parseColor("#ABAFBB")
            "50d"-> Color.parseColor("#92867B")
            "50n"-> Color.parseColor("#92867B")
            else -> R.drawable.no_background
        }
    }
}