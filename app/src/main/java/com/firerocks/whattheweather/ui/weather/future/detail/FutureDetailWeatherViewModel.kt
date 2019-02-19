package com.firerocks.whattheweather.ui.weather.future.detail

import androidx.lifecycle.ViewModel;
import com.firerocks.whattheweather.data.provider.UnitProvider
import com.firerocks.whattheweather.data.repository.ForecastRepository
import com.firerocks.whattheweather.internal.lazyDeferred
import com.firerocks.whattheweather.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureDetailWeatherViewModel(
    private val detailDate: LocalDate,
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weather by lazyDeferred {
        forecastRepository.getFutureWeatherByDate(detailDate, super.isMetricUnit)
    }

}
