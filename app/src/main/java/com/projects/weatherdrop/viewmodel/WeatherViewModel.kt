package com.projects.weatherdrop.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.weatherdrop.data.model.LocationModel
import com.projects.weatherdrop.data.model.WeatherResponse
import com.projects.weatherdrop.repository.WeatherRepository
import com.projects.weatherdrop.util.Result
import com.projects.weatherdrop.util.SharedPreferenceHelper
import com.projects.weatherdrop.util.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val sharedPreferenceHelper: SharedPreferenceHelper
): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _dataFetchState = MutableLiveData<Boolean>()
    val dataFetchState = _dataFetchState.asLiveData()

    val weatherResponse: MutableLiveData<WeatherResponse> = MutableLiveData()

    fun getWeather(locationModel: LocationModel){
        _isLoading.postValue(true)
        viewModelScope.launch {
            when(val result = weatherRepository.getWeather(locationModel)){
                is Result.Success -> {
                    _isLoading.value = false
                    if (result.data != null){
                        val response = result.data
                        _dataFetchState.value = true
                        weatherResponse.value = response
                    }
                }

                is Result.Error -> {
                    _isLoading.value = false
                    _dataFetchState.value = false
                }
                is Result.Loading -> _isLoading.postValue(true)
            }
        }
    }

    fun saveLocationDataSharedPref(id: String, name: String, locationModel: LocationModel){
        sharedPreferenceHelper.savePlaceId(id)
        sharedPreferenceHelper.savePlaceName(name)
        sharedPreferenceHelper.saveLocationModel(locationModel)
    }
}