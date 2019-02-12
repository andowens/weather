package com.firerocks.whattheweather.data.network

import androidx.lifecycle.LiveData
import com.firerocks.whattheweather.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )
}