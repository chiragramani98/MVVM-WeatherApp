package com.projects.weatherdrop.data.model

import java.text.SimpleDateFormat
import java.util.*

data class Hourly(
    val clouds: Float,
    val dew_point: Double,
    val dt: Int,
    val feels_like: Double,
    val humidity: Float,
    val pop: Float,
    val pressure: Float,
    val rain: Rain,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<WeatherXX>,
    val wind_deg: Float,
    val wind_gust: Double,
    val wind_speed: Double
) {

    fun getWeatherItem(): WeatherXX{
        return weather.first()
    }

    fun getTempString(): String {
        return temp.toString().substringBefore(".") + "°"
    }

    fun getDateAndTime(): String? {
        val sdf = SimpleDateFormat("hh a")
        val netDate = Date(dt.toLong() * 1000)
        val formattedDate = sdf.format(netDate)

        return formattedDate
    }
}