package com.example.beehivedata.network

import com.example.beehivedata.model.Moisture
import com.example.beehivedata.model.Temperature
import com.example.beehivedata.model.Weight
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

    fun fetchBeehiveTemp(id: String): Temperature? {
        var temp: Temperature? = null

        runBlocking {
            val fetchData = async { apiAccess.getBeehiveTemp(id) }
            runBlocking {
                temp = fetchData.await()
            }
        }
        return temp
    }

    fun fetchBeehiveMois(id: String): Moisture? {
        var moisture: Moisture? = null

        runBlocking {
            val fetchData = async { apiAccess.getBeehiveMois(id) }
            runBlocking {
                moisture = fetchData.await()
            }
        }
        return moisture
    }

    fun fetchBeehiveWeight(id: String): Weight? {
        var weight: Weight? = null

        runBlocking {
            val fetchData = async { apiAccess.getBeehiveWeight(id) }
            runBlocking {
                weight = fetchData.await()
            }
        }
        return weight
    }
}