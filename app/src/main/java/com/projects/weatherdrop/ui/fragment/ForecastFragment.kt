package com.projects.weatherdrop.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.projects.weatherdrop.R
import com.projects.weatherdrop.adapter.DailyWeatherAdapter
import com.projects.weatherdrop.adapter.HourlyWeatherAdapter
import com.projects.weatherdrop.databinding.FragmentForecastBinding
import com.projects.weatherdrop.util.SharedPreferenceHelper
import com.projects.weatherdrop.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForecastFragment : Fragment() {

    @Inject
    lateinit var sharedPreferenceHelper: SharedPreferenceHelper

    lateinit var binding: FragmentForecastBinding
    private val mWeatherViewModel: WeatherViewModel by viewModels()

    private val dailyWeatherAdapter by lazy { DailyWeatherAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_forecast, container, false)

        binding = FragmentForecastBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            dailyWeatherRecycler.apply {
                adapter = dailyWeatherAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        mWeatherViewModel.getWeather(sharedPreferenceHelper.getLocationModel())
        with(mWeatherViewModel){
            weatherResponse.observe(viewLifecycleOwner, Observer {
                dailyWeatherAdapter.submitList(it.daily)
            })
        }
    }
}