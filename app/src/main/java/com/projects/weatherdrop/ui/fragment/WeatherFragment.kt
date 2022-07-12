package com.projects.weatherdrop.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.projects.weatherdrop.BuildConfig
import com.projects.weatherdrop.R
import com.projects.weatherdrop.adapter.HourlyWeatherAdapter
import com.projects.weatherdrop.data.model.LocationModel
import com.projects.weatherdrop.databinding.FragmentWeatherBinding
import com.projects.weatherdrop.util.Constants
import com.projects.weatherdrop.util.NetworkUtil
import com.projects.weatherdrop.util.SharedPreferenceHelper
import com.projects.weatherdrop.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    @Inject
    lateinit var sharedPreferenceHelper: SharedPreferenceHelper

    lateinit var binding: FragmentWeatherBinding
    private val mWeatherViewModel: WeatherViewModel by viewModels()
    private val hourlyWeatherAdapter by lazy { HourlyWeatherAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_weather, container, false)

        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpSearchBar()

        hideAllViews(true)

        initiateRefresh()

        binding.swipeRefereshLayout.setOnRefreshListener {
            binding.weatherProgressbar.visibility = View.GONE
            initiateRefresh()
            binding.swipeRefereshLayout.isRefreshing = false
        }

        binding.hourlyWeatherRecycler.adapter = hourlyWeatherAdapter
        binding.hourlyWeatherRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.nextForecast.setOnClickListener {
            findNavController().navigate(R.id.action_weatherFragment_to_forecastFragment)
        }
    }

    private fun observeViewModel(){

        if (NetworkUtil.hasInternetConnection(requireContext())){
            if (sharedPreferenceHelper.getPlaceName() != null){
                binding.weatherLayout.visibility = View.VISIBLE
                binding.noPlaceSelectedLayout.visibility = View.GONE
                //observeViewModel()

                mWeatherViewModel.getWeather(sharedPreferenceHelper.getLocationModel())
                with(mWeatherViewModel){
                    weatherResponse.observe(viewLifecycleOwner, Observer { currentWeather ->
                        currentWeather.let {
                            binding.weatherData = it

                            hourlyWeatherAdapter.submitList(it.hourly)
                        }
                    })

                    dataFetchState.observe(viewLifecycleOwner) { state ->
                        when (state) {
                            true -> {
                                binding.apply {
                                    weatherLayout.visibility = View.VISIBLE
                                    weatherError.visibility = View.GONE
                                }
                            }
                            false -> {
                                binding.apply {
                                    weatherLayout.visibility = View.GONE
                                    weatherError.visibility = View.VISIBLE
                                    weatherProgressbar.visibility = View.GONE
                                    fetchingWeather.visibility = View.GONE
                                }
                            }
                        }
                    }

                    isLoading.observe(viewLifecycleOwner) { state ->
                        when (state) {
                            true -> {
                                binding.apply {
                                    weatherLayout.visibility = View.GONE
                                    weatherProgressbar.visibility = View.VISIBLE
                                    fetchingWeather.visibility = View.VISIBLE
                                }
                            }
                            false -> {
                                binding.apply {
                                    weatherProgressbar.visibility = View.GONE
                                    fetchingWeather.visibility = View.GONE
                                }
                            }
                        }
                    }
                }
            } else {
                binding.noPlaceSelectedLayout.visibility = View.VISIBLE
                binding.noInternetLayout.visibility = View.GONE
                binding.weatherLayout.visibility = View.GONE
            }
        } else {
            binding.noPlaceSelectedLayout.visibility = View.GONE
            binding.noInternetLayout.visibility = View.VISIBLE
            binding.weatherLayout.visibility = View.GONE
        }
    }

    private fun initiateRefresh(){
        observeViewModel()
    }

    private fun setUpSearchBar(){

        if (!Places.isInitialized()){
            Places.initialize(requireContext(), BuildConfig.MAPS_API_KEY)
        }

        val autoCompleteFragment = childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        autoCompleteFragment.setText(sharedPreferenceHelper.getPlaceName())

        autoCompleteFragment
            .setTypeFilter(TypeFilter.CITIES)
            .setActivityMode(AutocompleteActivityMode.FULLSCREEN)
            .setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))

        autoCompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                mWeatherViewModel.saveLocationDataSharedPref(
                    place.id!!,
                    place.name!!,
                    LocationModel(place.latLng!!.latitude, place.latLng!!.longitude)
                )
                initiateRefresh()
            }
            override fun onError(status: Status) {
                Toast.makeText(context, status.statusMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun hideAllViews(state: Boolean){
        if (state){
            binding.weatherLayout.visibility = View.GONE
            binding.noInternetLayout.visibility = View.GONE
            binding.noPlaceSelectedLayout.visibility = View.GONE
        }
    }
}