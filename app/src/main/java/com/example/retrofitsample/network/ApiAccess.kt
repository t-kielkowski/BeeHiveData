package com.example.retrofitsample.network

import com.example.retrofitsample.model.BatteryLevel
import com.example.retrofitsample.model.Moisture
import com.example.retrofitsample.model.Temperature
import com.example.retrofitsample.model.Weight
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ApiAccess() {
    private val networkInterface : ApiEndPoint

    companion object {
        var BaseURl = "http://10.0.2.2:5064/"
    }

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BaseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        networkInterface = retrofit.create(ApiEndPoint::class.java)
    }

    suspend fun getBeehiveList() :  ArrayList<String> {
        return networkInterface.getBeehiveList()
    }

    suspend fun getBeehiveTemp(id: String, dateFrom: String? = null, dateTo: String? = null) : ArrayList<Temperature> {
        return networkInterface.getBeehiveTemp(id, dateFrom, dateTo)
    }

    suspend fun getBeehiveMois(id: String, dateFrom: String? = null, dateTo: String? = null) : ArrayList<Moisture> {
        return networkInterface.getBeehiveMois(id, dateFrom, dateTo)
    }
    suspend fun getBeehiveWeight(id: String, dateFrom: String? = null, dateTo: String? = null) : ArrayList<Weight> {
        return networkInterface.getBeehiveWeight(id, dateFrom, dateTo)
    }
    suspend fun getBeehiveBatteryLevel(id: String, dateFrom: String? = null, dateTo: String? = null) : ArrayList<BatteryLevel> {
        return networkInterface.getBeehiveBatteryLevel(id, dateFrom, dateTo)
    }
}