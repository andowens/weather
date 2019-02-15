package com.firerocks.whattheweather.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.firerocks.whattheweather.data.provider.UnitProvider
import com.firerocks.whattheweather.data.repository.ForecastRepository
import com.firerocks.whattheweather.internal.UnitSystem
import com.firerocks.whattheweather.internal.lazyDeferred
import com.firerocks.whattheweather.ui.base.WeatherViewModel

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(super.isMetricUnit)
    }

}
