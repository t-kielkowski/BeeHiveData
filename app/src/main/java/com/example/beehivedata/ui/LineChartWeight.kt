package com.example.beehivedata.ui

import android.os.Bundle
import com.example.beehivedata.R
import com.example.beehivedata.chart.ChartType
import com.github.mikephil.charting.charts.LineChart

class LineChartWeight :LineChartActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.line_chart)

        val id = intent.getStringExtra("id")

        if(id != null)
        {
            var weight = dataRetriever.fetchBeehiveWeight(id)

            if (weight != null)
            {
                var lineChart : LineChart = findViewById(R.id.lineChart)
                chartPresenter.drawWeightChart(weight, lineChart)

                setButtons(id, "TEMP", ChartType.Temperature, "MOIS", ChartType.Moisture)
            }
        }
    }
}