package com.projects.weatherdrop.data.source

import com.projects.weatherdrop.data.model.LocationModel
import com.projects.weatherdrop.data.model.WeatherResponse
import com.projects.weatherdrop.data.source.retrofit.WeatherApiService
import com.projects.weatherdrop.di.IoDispatcher
import com.projects.weatherdrop.util.Constants
import com.projects.weatherdrop.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherDataSource @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val weatherApiService: WeatherApiService
) {
    suspend fun getWeather(locationModel: LocationModel): Result<WeatherResponse> =
        withContext(ioDispatcher){
            return@withContext try {
                val result = weatherApiService.getCurrentWeather(locationModel.lat, locationModel.lon, Constants.METRIC)

                if (result.isSuccessful){
                    val weatherResponse = result.body()
                    Result.Success(weatherResponse)
                } else {
                    Result.Success(null)
                }
            } catch (exception: Exception){
                Result.Error(exception)
            }
        }
}