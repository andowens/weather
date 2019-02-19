package com.firerocks.whattheweather.ui.weather.future.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.firerocks.whattheweather.R
import com.firerocks.whattheweather.data.db.LocalDateConverter
import com.firerocks.whattheweather.internal.DateNotFoundException
import com.firerocks.whattheweather.internal.glide.GlideApp
import com.firerocks.whattheweather.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.future_detail_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import kotlin.math.max

class FutureDetailWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val viewModelFactoryInstanceFactory : ((LocalDate) -> FutureDetailWeatherViewModelFactory) by factory()

    private lateinit var viewModel: FutureDetailWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_detail_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let { FutureDetailWeatherFragmentArgs.fromBundle(it) }
        val date = LocalDateConverter.stringToDate(safeArgs?.dateString) ?: throw DateNotFoundException()

        viewModel = ViewModelProviders.of(this,
            viewModelFactoryInstanceFactory(date)).get(FutureDetailWeatherViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val futureWeather = viewModel.weather.await()
        val weatherLocation = viewModel.weatherLocation.await()

        weatherLocation.observe(this@FutureDetailWeatherFragment, Observer { location ->
            if (location == null) return@Observer
            updateLocation(location.name)
        })

        futureWeather.observe(this@FutureDetailWeatherFragment, Observer { weatherEntry ->
            if (weatherEntry == null ) return@Observer

            group_loading.visibility = View.GONE

            updateDate(weatherEntry.date)
            updateTemperatures(weatherEntry.avgTemperature, weatherEntry.minTemperature,
                weatherEntry.maxTemperature)
            updateCondition(weatherEntry.conditionText)
            updatePrecipitation(weatherEntry.totalPrecipitation)
            updateWindSpeed(weatherEntry.maxWindSpeed)
            updateVisibility(weatherEntry.avgVisibilityDistance)
            updateUv(weatherEntry.uv)

            GlideApp.with(this@FutureDetailWeatherFragment)
                .load("https:" + weatherEntry.conditionIconUrl)
                .into(imageView_condition_icon)
        })
    }

    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String) : String {
        return if (viewModel.isMetricUnit) metric else imperial
    }

    private fun updateUv(uv: Double) {
        textView_uv.text = "UV: $uv"
    }

    private fun updateVisibility(avgVisibilityDistance: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("km", "mi.")
        textView_visibility.text = "Visibility: $avgVisibilityDistance $unitAbbreviation"
    }

    private fun updateWindSpeed(maxWindSpeed: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("kph", "mph")
        textView_wind.text = "Wind speed: $maxWindSpeed $unitAbbreviation"
    }

    private fun updatePrecipitation(totalPrecipitation: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("mm", "in")
        textView_precipitation.text = "Precipitation: $totalPrecipitation $unitAbbreviation"
    }

    private fun updateCondition(conditionText: String) {
        textView_condition.text = conditionText
    }

    private fun updateTemperatures(avgTemperature: Double, minTemperature: Double, maxTemperature: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("°C", "°F")
        textView_temperature.text = "$avgTemperature$unitAbbreviation"
        textView_min_max.text = "Min: $minTemperature$unitAbbreviation Max: $maxTemperature$unitAbbreviation"
    }

    private fun updateDate(date: LocalDate) {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle =
            date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
    }

    private fun updateLocation(name: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = name
    }

}
