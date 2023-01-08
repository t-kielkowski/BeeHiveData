package com.example.beehivedata.ui

import android.os.Bundle
import com.example.beehivedata.R
import com.example.beehivedata.chart.ChartType
import com.github.mikephil.charting.charts.LineChart

class LineChartMoisture : LineChartActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.line_chart)

        val id = intent.getStringExtra("id")

        if(id != null)
        {
            var mois = dataRetriever.fetchBeehiveMois(id)

            if (mois != null)
            {
                var lineChart : LineChart = findViewById(R.id.lineChart)
                chartPresenter.drawMoistureChart(mois, lineChart)

                setButtons(id, "WEIGHT", ChartType.Weight, "TEMP", ChartType.Temperature)
            }
        }
    }
}