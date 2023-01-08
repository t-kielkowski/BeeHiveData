package com.example.beehivedata.chart

import com.example.beehivedata.R
import com.example.beehivedata.model.Moisture
import com.example.beehivedata.model.Temperature
import com.example.beehivedata.model.Weight
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class ChartPresenter {

    fun drawWeightChart(weightData: Weight, lineChart: LineChart) {
        var index = 0f
        var readingDate: MutableList<String> = mutableListOf()
        var weight = ArrayList<Entry>()

        for (reading in weightData.readingTime)
            readingDate.add(getXAxisValue(reading))

        for (reading in weightData.weight)
            weight.add(Entry(index++, reading.toFloat()))

        setUpLineChart(readingDate, lineChart)
        setDataToLineChart(weight, "Weight", lineChart)
    }

    fun drawTempChart(temperatureData: Temperature, lineChart: LineChart) {
        var index = 0f
        var readingDate: MutableList<String> = mutableListOf()
        var temp = ArrayList<Entry>()

        for (reading in temperatureData.readingTime)
            readingDate.add(getXAxisValue(reading))

        for (reading in temperatureData.temperature)
            temp.add(Entry(index++, reading.toFloat()))

        setUpLineChart(readingDate, lineChart)
        setDataToLineChart(temp, "Temperature", lineChart)
    }

    fun drawMoistureChart(moistureData: Moisture, lineChart: LineChart) {
        var index = 0f
        var readingDate: MutableList<String> = mutableListOf()
        var mois = ArrayList<Entry>()

        for (reading in moistureData.readingTime)
            readingDate.add(getXAxisValue(reading))

        for (reading in moistureData.moisture)
            mois.add(Entry(index++, reading.toFloat()))

        setUpLineChart(readingDate, lineChart)
        setDataToLineChart(mois, "Moisture", lineChart)
    }


    private fun getXAxisValue(readingTime: String): String {
        val splitResult = readingTime.split(' ')
        return splitResult[0].substring(IntRange(0, 4)) + " " + splitResult[1].substring(
            IntRange(
                0,
                4
            )
        )
    }

    private fun setUpLineChart(readingDate: List<String>, lineChart: LineChart) {
        with(lineChart) {
            animateX(1200, Easing.EaseInSine)
            description.isEnabled = false
            xAxis.setDrawGridLines(true)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1F
            xAxis.valueFormatter = MyAxisFormatter(readingDate)
            xAxis.textSize = 7F

            axisRight.isEnabled = false
            //extraRightOffset = 30f

            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.textSize = 15F
            legend.form = Legend.LegendForm.LINE
        }
    }

    private fun setDataToLineChart(data: ArrayList<Entry>, label: String, lineChart: LineChart) {

        val chart = LineDataSet(data, label)
        chart.setDrawCircles(false)
        chart.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        chart.valueTextSize = 11f
        chart.valueTextColor = R.color.radBtnChecked
        chart.lineWidth = 3f
        chart.color = R.color.lineChartBackground
        chart.setDrawFilled(true)
        //chart.fillColor =  R.color.lineChartBackground
        //chart.fillAlpha = 80

        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(chart)
        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate()

        if (data.size > 15)
            lineChart.setVisibleXRange(5f, 15f)

        lineChart.moveViewToX((data.size).toFloat())
    }

    internal inner class MyAxisFormatter(readingDate: List<String>) : IndexAxisValueFormatter() {
        private val readings = readingDate

        override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
            val index = value.toInt()
            return if (index < readings.size) {
                readings[index]
            } else {
                null
            }
        }
    }
}