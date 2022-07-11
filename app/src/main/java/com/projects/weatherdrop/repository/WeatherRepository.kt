package com.projects.weatherdrop.repository

import com.projects.weatherdrop.data.model.LocationModel
import com.projects.weatherdrop.data.model.WeatherResponse
import com.projects.weatherdrop.data.source.WeatherDataSource
import com.projects.weatherdrop.data.source.retrofit.WeatherApiService
import com.projects.weatherdrop.di.IoDispatcher
import com.projects.weatherdrop.util.Constants
import com.projects.weatherdrop.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val weatherDataSource: WeatherDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getCurrentWeather(locationModel: LocationModel): Response<WeatherResponse> =
        weatherApiService.getCurrentWeather(locationModel.lat, locationModel.lon, Constants.METRIC)

    suspend fun getWeather(locationModel: LocationModel): Result<WeatherResponse> =
        withContext(ioDispatcher){
            when(val response = weatherDataSource.getWeather(locationModel)){
                is Result.Success ->{
                    if (response.data != null){
                        Result.Success(response.data)
                    } else{
                        Result.Success(null)
                    }
                }

                is Result.Error -> {
                    Result.Error(response.exception)
                }

                else -> Result.Loading
            }
        }
}