package com.example.beehivedata.ui

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.beehivedata.R
import com.example.beehivedata.network.BeehiveDataRetriever

class MainActivity : AppCompatActivity() {
    private val dataRetriever: BeehiveDataRetriever = BeehiveDataRetriever()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateSpinnerList()
        setButtonOnClick()
    }

    private fun updateSpinnerList() {
        val beeList = dataRetriever.fetchBeehiveList()
        var adapterList = ArrayList<String>()
        for (beeHive in beeList)
            adapterList.add(beeHive.replace("_", " "))

        val adapter = CustomSpinnerAdapter(
            this,
            adapterList
        )
        val spinner: Spinner = findViewById(R.id.beeList)
        spinner.adapter = adapter
    }

    private fun setButtonOnClick(){
        val spinner: Spinner = findViewById(R.id.beeList)
        val drawChartBtn: Button = findViewById(R.id.drawChart);

        drawChartBtn.setOnClickListener {
            val (id, chartType) = getIdAndChartType(spinner)
            startActivity(com.example.beehivedata.chart.createIntent(this, id, chartType))
        }
    }

    private fun getIdAndChartType(spinner: Spinner): Pair<String, String> {
        var id = spinner.selectedItem.toString().replace(" ", "_")
        val selectedId = findViewById<RadioGroup>(R.id.radioSensorType).checkedRadioButtonId;
        val radioBtn: Button = findViewById(selectedId)
        val chartType = radioBtn.text.toString()

        return Pair(id, chartType)
    }
}
