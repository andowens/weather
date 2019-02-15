package com.firerocks.whattheweather.data.provider

import com.firerocks.whattheweather.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}