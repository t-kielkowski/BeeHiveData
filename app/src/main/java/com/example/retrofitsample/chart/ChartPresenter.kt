package com.example.retrofitsample.chart

import com.example.retrofitsample.model.Temperature
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

class ChartPresenter  {

    fun drawTempChart(temperature: ArrayList<Temperature>, lineChart : LineChart) {
        var index = 0f
        var readingDate: MutableList<String> = mutableListOf()
        var temp = ArrayList<Entry>()

        for (reading in temperature)
            readingDate.add(GetXAxisValue(reading))

        for (reading in temperature)
            temp.add(Entry(index++, reading.temperature.toFloat()))

        setUpLineChart(readingDate, lineChart)
        setDataToLineChart(temp, "Temperature", lineChart)
    }

    private fun GetXAxisValue(reading: Temperature) : String{
        val splitResult = reading.readingTime.split(' ')
        return splitResult[0].substring(IntRange(0,4)) + " " + splitResult[1].substring(IntRange(0, 4))
    }

    private fun setUpLineChart(readingDate: List<String>, lineChart : LineChart) {
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

    private fun setDataToLineChart(data: ArrayList<Entry>, label: String, lineChart : LineChart) {

        val chart = LineDataSet(data, label)
        chart.setDrawFilled(true)
        chart.lineWidth = 3f
        chart.valueTextSize = 15f
        chart.mode = LineDataSet.Mode.CUBIC_BEZIER
        //chart.color = ContextCompat.getColor(this, R.color.red)
        //chart.valueTextColor = ContextCompat.getColor(this, R.color.red)
        //chart.enableDashedLine(20F, 10F, 0F)
        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(chart)
        val lineData = LineData(dataSet)

        lineChart.data = lineData
        lineChart.invalidate()

        if(data.size>15)
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