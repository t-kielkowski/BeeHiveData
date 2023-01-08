package com.example.beehivedata.chart

import android.content.Context
import android.content.Intent
import com.example.beehivedata.ui.LineChartMoisture
import com.example.beehivedata.ui.LineChartTemp
import com.example.beehivedata.ui.LineChartWeight

enum class ChartType {
    Temperature, Moisture, Weight
}

fun createIntent(context: Context, id: String, chartType: String): Intent {
    val type = when (chartType) {
        ChartType.Temperature.name -> LineChartTemp()
        ChartType.Moisture.name -> LineChartMoisture()
        else -> LineChartWeight()
    }

    val intent = Intent(context, type::class.java)
    intent.putExtra("id", id)
    return intent
}
