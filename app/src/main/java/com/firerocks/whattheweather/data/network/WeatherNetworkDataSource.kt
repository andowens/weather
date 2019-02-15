package com.firerocks.whattheweather.data.network

import androidx.lifecycle.LiveData
import com.firerocks.whattheweather.data.network.response.CurrentWeatherResponse
import com.firerocks.whattheweather.data.network.response.FutureWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
    val downloadedFutureWeather: LiveData<FutureWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )

    suspend fun fetchFutureWeather(
        location: String,
        languageCode: String
    )
}