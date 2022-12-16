package com.example.beehivedata.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.beehivedata.R
import com.example.beehivedata.chart.ChartPresenter
import com.example.beehivedata.network.BeehiveDataRetriever
import com.github.mikephil.charting.charts.LineChart

class MainActivity : AppCompatActivity() {

    private val dataRetriever: BeehiveDataRetriever = BeehiveDataRetriever()
    private val chartPresenter: ChartPresenter = ChartPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        udpateSpinnerList()
    }

    private fun udpateSpinnerList() {

        val beeList = dataRetriever.fetchBeehiveList()

        var listOfBeeHives: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, beeList)
        val spinner: Spinner = findViewById(R.id.spinner)

        spinner.adapter = listOfBeeHives
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val id = spinner.selectedItem.toString()

                if (id == "UL_1") {
                    setContentView(R.layout.temp_chart)

                    var temp = dataRetriever.fetchBeehiveTemp(id)

                    if (temp != null)
                    {
                        var lineChart : LineChart = findViewById(R.id.lineChart)
                        chartPresenter.drawTempChart(temp, lineChart)
                    }
                }
            }
        }
    }
}
