package com.bubbletastic.githubjobs.service

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import java.io.InputStream
import java.net.URL
import java.util.*

class NetworkHandlerMock : NetworkHandler {

    override fun get(url: String): InputStream {
        val pageNum = getQueryMapFromUrl(url)["page"]?.toInt()
        if (pageNum!! < 4) {
            return generateJsonArrayOfObjects(50).toJsonString().byteInputStream()
        }
        return generateJsonArrayOfObjects(10).toJsonString().byteInputStream()
    }

    private fun getQueryMapFromUrl(url: String): Map<String, String> {
        val queryParams = mutableMapOf<String, String>() //Assuming no array values. In this project I control the code so that's fine.
        URL(url).query
                .split("&")
                .forEach {
                    val param = it.split("=")
                    queryParams.put(param[0], param[1])
                }
        return queryParams
    }

    private fun generateJsonArrayOfObjects(numObjects: Int): JsonArray<JsonObject> {
        val jsonArray = JsonArray<JsonObject>()
        for (index in 1..numObjects) {
            val jsonObject = JsonObject()
            jsonObject.put("creation_index", index)
            jsonObject.put("id", UUID.randomUUID().toString())
            jsonArray.add(jsonObject)
        }
        return jsonArray
    }
}