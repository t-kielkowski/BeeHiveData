package com.example.beehivedata.network

import com.example.beehivedata.model.BatteryLevel
import com.example.beehivedata.model.Moisture
import com.example.beehivedata.model.Temperature
import com.example.beehivedata.model.Weight
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
    ): Temperature

    @GET("/api/Beehive/moisture")
    suspend fun getBeehiveMois(
        @Query("id") id: String,
        @Query("dateFrom") dateFrom: String?,
        @Query("dateTo") dateTo: String?
    ): Moisture

    @GET("/api/Beehive/weight")
    suspend fun getBeehiveWeight(
        @Query("id") id: String,
        @Query("dateFrom") dateFrom: String?,
        @Query("dateTo") dateTo: String?
    ): Weight

    @GET("/api/Beehive/temperature")
    suspend fun getBeehiveBatteryLevel(
        @Query("id") id: String,
        @Query("dateFrom") dateFrom: String?,
        @Query("dateTo") dateTo: String?
    ): BatteryLevel
}