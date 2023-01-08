package com.example.beehivedata.ui

import android.os.Bundle
import com.example.beehivedata.R
import com.example.beehivedata.chart.ChartType
import com.github.mikephil.charting.charts.LineChart

class LineChartTemp : LineChartActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.line_chart)

        val id = intent.getStringExtra("id")

        if(id != null)
        {
            var temp = dataRetriever.fetchBeehiveTemp(id)

            if (temp != null)
            {
                var lineChart : LineChart = findViewById(R.id.lineChart)
                chartPresenter.drawTempChart(temp, lineChart)

                setButtons(id, "WEIGHT", ChartType.Weight, "MOIS", ChartType.Moisture)
            }
        }
    }
}