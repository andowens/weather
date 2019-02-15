package com.firerocks.whattheweather.ui.weather.future.list

import androidx.lifecycle.ViewModel;
import com.firerocks.whattheweather.data.provider.UnitProvider
import com.firerocks.whattheweather.data.repository.ForecastRepository
import com.firerocks.whattheweather.internal.lazyDeferred
import com.firerocks.whattheweather.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureListWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weatherEntries by lazyDeferred {
        forecastRepository.getFutureWeatherList(LocalDate.now(), super.isMetricUnit)
    }
}
