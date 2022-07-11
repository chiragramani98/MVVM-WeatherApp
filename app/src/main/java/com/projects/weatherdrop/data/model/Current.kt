package com.projects.weatherdrop.data.model

data class Current(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_speed: Double
) {
    fun getWeatherItem(): Weather{
        return weather.first()
    }

    fun getTempString(): String {
        return temp.toString().substringBefore(".") + "°"
    }

    fun getIcon(): String{
        return weather.first().icon
    }

    fun getDescription(): String{
        return getWeatherItem().description
    }

    fun getFeelsLikeTemp(): String{
        return feels_like.toString().substringBefore(".") + "°"
    }

    fun getHumidityString(): String{
        return humidity.toString() + "%"
    }

    fun getWindSpeed(): String{
        return wind_speed.toString()+" m/s"
    }

    fun getPressureString(): String{
        return pressure.toString()+" mBar"
    }

}