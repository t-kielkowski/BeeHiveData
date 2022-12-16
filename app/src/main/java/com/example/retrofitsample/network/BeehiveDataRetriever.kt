package com.example.retrofitsample.network

import com.example.retrofitsample.model.Moisture
import com.example.retrofitsample.model.Temperature
import com.example.retrofitsample.model.Weight
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class BeehiveDataRetriever {
    private val apiAccess: ApiAccess = ApiAccess()

    fun fetchBeehiveList(): ArrayList<String> {
        var beehiveList : ArrayList<String>

        runBlocking {
            val fetchData = async { apiAccess.getBeehiveList() }
            runBlocking {
                beehiveList = fetchData.await()
            }
        }
        return beehiveList
    }

    fun fetchBeehiveTemp(id: String): ArrayList<Temperature>? {
        var temp: ArrayList<Temperature>? = null

        runBlocking {
            val fetchData = async { apiAccess.getBeehiveTemp(id) }
            runBlocking {
                temp = fetchData.await()
            }
        }
        return temp
    }

    fun fetchBeehiveMois(id: String): ArrayList<Moisture>? {
        var moisture: ArrayList<Moisture>? = null

        runBlocking {
            val fetchData = async { apiAccess.getBeehiveMois(id) }
            runBlocking {
                moisture = fetchData.await()
            }
        }
        return moisture
    }

    fun fetchBeehiveWeight(id: String): ArrayList<Weight>? {
        var weight: ArrayList<Weight>? = null

        runBlocking {
            val fetchData = async { apiAccess.getBeehiveWeight(id) }
            runBlocking {
                weight = fetchData.await()
            }
        }
        return weight
    }
}