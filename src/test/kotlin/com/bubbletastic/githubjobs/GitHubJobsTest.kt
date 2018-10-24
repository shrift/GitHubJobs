package com.bubbletastic.githubjobs

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.bubbletastic.githubjobs.service.JobsService
import com.bubbletastic.githubjobs.service.NetworkHandler
import org.junit.jupiter.api.Test
import java.io.InputStream

@Suppress("UNCHECKED_CAST")
class GitHubJobsTest {

    private val expectedOutput =
            """    - Full Time: 25%
    - Temp: 25%
    - Part Time: 50%
"""

    @Test
    fun testPrintPositionTypes() {
        val positions = Parser().parse(testJsonNewYork.byteInputStream()) as JsonArray<JsonObject>
        val actualOutput = GitHubJobs().buildPositionsString(positions).toString()
        assert(expectedOutput == actualOutput)
    }

    @Test
    fun showFakeValues() {
        GitHubJobs(JobsService(NetworkHandlerFakeData())).findPredefinedJobs()
    }

    class NetworkHandlerFakeData : NetworkHandler {
        override fun get(url: String): InputStream {
            //This is pretty hacky, but I wanted to show something quickly that actually has all the position types.
            if (url.contains("Chicago")) {
                return testJsonChicago.byteInputStream()
            } else if (url.contains("New+York")) {
                return testJsonNewYork.byteInputStream()
            } else if (url.contains("Boston")) {
                return testJsonBoston.byteInputStream()
            } else if (url.contains("Boulder")) {
                return testJsonBoulder.byteInputStream()
            } else {
                return "[]".byteInputStream()
            }
        }

    }


}