package com.firerocks.whattheweather.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.firerocks.whattheweather.R

class CurrentWeatherFratment : Fragment() {

    companion object {
        fun newInstance() = CurrentWeatherFratment()
    }

    private lateinit var viewModel: CurrentWeatherFratmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fratment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherFratmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
