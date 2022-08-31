package com.projects.weatherdrop.data.model

import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*

data class Daily(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: FeelsLike,
    val humidity: Int,
    val moon_phase: Double,
    val moonrise: Int,
    val moonset: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val uvi: Float,
    val weather: List<WeatherX>,
    val wind_deg: Int,
    val wind_gust: Double,
    val wind_speed: Double
){
    fun getWeatherItem(): WeatherX{
        return weather.first()
    }

    fun getIcon(): String{
        return getWeatherItem().icon
    }

    fun getDescription(): String{
        return getWeatherItem().description
    }

    fun getMaxTemp(): String{
        return temp.max.toString().substringBefore(".") + "°"
    }

    fun getMinTemp(): String{
        return temp.min.toString().substringBefore(".") + "°"
    }

    fun getDateAndDay(): String? {
        val sdf = SimpleDateFormat("dd MMMM/yyyy")
        val netDate = Date(dt.toLong() * 1000)
        val formattedDate = sdf.format(netDate)

        val date = formattedDate.substringBefore("/").toString()
        val dayOfWeek = getDayOfWeek()
        val dateAndDay = dayOfWeek.toString()+", "+date

        return dateAndDay
    }

    fun getDayOfWeek(): DayOfWeek{
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(dt.toLong() * 1000)
            val formattedDate = sdf.format(netDate)

            LocalDate.of(
                formattedDate.substringAfterLast("/").toInt(),
                formattedDate.substringAfter("/").take(2).toInt(),
                formattedDate.substringBefore("/").toInt()
            ).dayOfWeek
        } catch (e: Exception) {
            e.printStackTrace()
            DayOfWeek.MONDAY
        }
    }
}