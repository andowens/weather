package com.firerocks.whattheweather.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.firerocks.whattheweather.data.provider.UnitProvider
import com.firerocks.whattheweather.data.repository.ForecastRepository
import com.firerocks.whattheweather.internal.UnitSystem
import com.firerocks.whattheweather.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}
