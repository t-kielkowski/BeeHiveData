package com.example.beehivedata.ui

import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.beehivedata.R
import com.example.beehivedata.chart.ChartPresenter
import com.example.beehivedata.chart.ChartType
import com.example.beehivedata.network.BeehiveDataRetriever

open class LineChartActivity : AppCompatActivity() {
    val dataRetriever: BeehiveDataRetriever = BeehiveDataRetriever()
    val chartPresenter: ChartPresenter = ChartPresenter()

    fun setButtons(id: String, btnLeftName: String, btnLeftType : ChartType, btnRightName : String, btnRightType : ChartType){
        val buttonLeft: Button = findViewById(R.id.btnLeft)
        buttonLeft.text = btnLeftName

        buttonLeft.setOnClickListener {
            startActivity(com.example.beehivedata.chart.createIntent(this, id, btnLeftType.toString()))
        }

        val buttonRight: Button = findViewById(R.id.btnRight)
        buttonRight.text = btnRightName
        
        buttonRight.setOnClickListener {
            startActivity(com.example.beehivedata.chart.createIntent(this, id, btnRightType.toString()))
        }
    }
}