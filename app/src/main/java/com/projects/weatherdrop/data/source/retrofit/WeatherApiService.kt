package com.projects.weatherdrop.data.source.retrofit

import com.projects.weatherdrop.BuildConfig
import com.projects.weatherdrop.data.model.WeatherResponse
import com.projects.weatherdrop.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("data/2.5/onecall")
    suspend fun getCurrentWeather(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("units")
        units: String,
        @Query("appid")
        apiKey: String = BuildConfig.WEATHER_API_KEY
    ): Response<WeatherResponse>
}