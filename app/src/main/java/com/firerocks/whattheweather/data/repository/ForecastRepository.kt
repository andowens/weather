package com.firerocks.whattheweather.data.repository

import androidx.lifecycle.LiveData
import com.firerocks.whattheweather.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {

    suspend fun getCurrentWeather(metric: Boolean) : LiveData<out UnitSpecificCurrentWeatherEntry>
}