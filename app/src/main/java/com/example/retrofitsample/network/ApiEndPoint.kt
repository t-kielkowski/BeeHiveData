package com.example.retrofitsample.network

import com.example.retrofitsample.model.BatteryLevel
import com.example.retrofitsample.model.Moisture
import com.example.retrofitsample.model.Temperature
import com.example.retrofitsample.model.Weight
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface ApiEndPoint {
    @GET("/api/Beehive/beehiveList")
    suspend fun getBeehiveList(): ArrayList<String>

    @GET("/api/Beehive/temperature")
    suspend fun getBeehiveTemp(
        @Query("id") id: String,
        @Query("dateFrom") dateFrom: String?,
        @Query("dateTo") dateTo: String?
    ): ArrayList<Temperature>

    @GET("/api/Beehive/moisture")
    suspend fun getBeehiveMois(
        @Query("id") id: String,
        @Query("dateFrom") dateFrom: String?,
        @Query("dateTo") dateTo: String?
    ): ArrayList<Moisture>

    @GET("/api/Beehive/weight")
    suspend fun getBeehiveWeight(
        @Query("id") id: String,
        @Query("dateFrom") dateFrom: String?,
        @Query("dateTo") dateTo: String?
    ): ArrayList<Weight>

    @GET("/api/Beehive/temperature")
    suspend fun getBeehiveBatteryLevel(
        @Query("id") id: String,
        @Query("dateFrom") dateFrom: String?,
        @Query("dateTo") dateTo: String?
    ): ArrayList<BatteryLevel>
}