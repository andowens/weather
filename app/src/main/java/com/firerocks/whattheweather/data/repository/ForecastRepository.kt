package com.firerocks.whattheweather.data.repository

import androidx.lifecycle.LiveData
import com.firerocks.whattheweather.data.db.entity.WeatherLocation
import com.firerocks.whattheweather.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry
import com.firerocks.whattheweather.data.db.unitlocalized.future.detail.UnitSpecificDetailFutureWeatherEntry
import com.firerocks.whattheweather.data.db.unitlocalized.future.list.UnitSpecifiedSimpleFutureWeatherEntry
import org.threeten.bp.LocalDate

interface ForecastRepository {

    suspend fun getCurrentWeather(metric: Boolean) : LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation() : LiveData<WeatherLocation>
    suspend fun getFutureWeatherByDate(date: LocalDate, metric: Boolean) : LiveData<out UnitSpecificDetailFutureWeatherEntry>
    suspend fun getFutureWeatherList(startDate: LocalDate, metric: Boolean): LiveData<out List<UnitSpecifiedSimpleFutureWeatherEntry>>
}