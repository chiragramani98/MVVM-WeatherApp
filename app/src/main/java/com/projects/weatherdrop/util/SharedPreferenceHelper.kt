package com.projects.weatherdrop.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import com.google.gson.Gson
import com.projects.weatherdrop.data.model.LocationModel

class SharedPreferenceHelper {

    companion object{
        const val PLACE_ID = "Place Id"
        const val PLACE_NAME = "Place Name"
        const val LOCATION ="LOCATION"

        private var prefs: SharedPreferences? = null

        @Volatile
        private var INSTANCE: SharedPreferenceHelper? = null

        fun getInstance(context: Context): SharedPreferenceHelper{
            synchronized(this){
                val instance = INSTANCE
                if (instance == null){
                    prefs = PreferenceManager.getDefaultSharedPreferences(context)
                    INSTANCE = instance
                }
                return SharedPreferenceHelper()
            }
        }
    }

    fun savePlaceId(placeId: String){
        prefs?.edit(commit = true){
            putString(PLACE_ID, placeId)
        }
    }

    fun getPlaceId() = prefs?.getString(PLACE_ID, null)

    fun savePlaceName(placeName: String){
        prefs?.edit(commit = true){
            putString(PLACE_NAME, placeName)
        }
    }

    fun getPlaceName() = prefs?.getString(PLACE_NAME, null)

    fun saveLocationModel(locationModel: LocationModel){
        prefs?.edit(commit = true){
            val gson = Gson()
            val json = gson.toJson(locationModel)
            putString(LOCATION, json)
        }
    }

    fun getLocationModel(): LocationModel{
        val json = prefs?.getString(LOCATION, "{}")
        return Gson().fromJson(json, LocationModel::class.java)
    }
}