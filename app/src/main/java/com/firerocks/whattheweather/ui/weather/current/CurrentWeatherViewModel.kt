package com.firerocks.whattheweather.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.firerocks.whattheweather.data.repository.ForecastRepository
import com.firerocks.whattheweather.internal.UnitSystem
import com.firerocks.whattheweather.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    private val unitSystem = UnitSystem.METRIC

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
