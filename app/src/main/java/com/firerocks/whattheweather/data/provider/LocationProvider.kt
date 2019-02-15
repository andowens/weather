package com.firerocks.whattheweather.data.provider

import com.firerocks.whattheweather.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation) : Boolean
    suspend fun getPreferredLocationString() : String
}