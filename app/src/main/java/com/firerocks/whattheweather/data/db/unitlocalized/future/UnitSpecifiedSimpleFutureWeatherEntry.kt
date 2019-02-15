package com.firerocks.whattheweather.data.db.unitlocalized.future

import org.threeten.bp.LocalDate

interface UnitSpecifiedSimpleFutureWeatherEntry {
    val date: LocalDate
    val avgTemperature: Double
    val conditionText: String
    val conditionIconUrl: String
}