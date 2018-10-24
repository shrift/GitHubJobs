package com.bubbletastic.githubjobs

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import org.junit.jupiter.api.Test

@Suppress("UNCHECKED_CAST")
class GitHubJobsTest {

    private val testJson = """
[
{
  "id": "262e4f2e-d186-11e8-8872-87e699571f8c",
  "location": "New York, NY",
  "type": "Full Time"
}, {
  "id": "76e2a394-95d0-11e8-8bed-67e83dee4a94",
  "location": "New York, NY",
  "type": "Full Time"
}, {
  "id": "bbc72f80-9a57-11e8-93e2-effc1a9c1d65",
  "location": "New York, NY",
  "type": "Temp"
}, {
  "id": "be462f2c-9a57-11e8-8dc5-7ba6ce68e632",
  "location": "New York, NY",
  "type": "Temp"
}, {
  "id": "c078b45e-9a57-11e8-867e-4039dfbc3694",
  "location": "New York, NY",
  "type": "Part Time"
}, {
  "id": "c078b45e-9a57-11e8-867e-40323kj23jk9",
  "location": "New York, NY",
  "type": "Part Time"
}, {
  "id": "c2928c56-9a57-11e8-917e-143d2a687448",
  "location": "New York, NY",
  "type": "Part Time"
}, {
  "id": "ef024d84-c816-11e8-9f03-6e04b79d9f73",
  "location": "New York",
  "type": "Part Time"
}
]
""".trimIndent()

    private val expectedOutput =
            """    - Full Time: 25%
    - Temp: 25%
    - Part Time: 50%
"""

    @Test
    fun testPrintPositionTypes() {
        val positions = Parser().parse(testJson.byteInputStream()) as JsonArray<JsonObject>
        val actualOutput = GitHubJobs().buildPositionsString(positions).toString()
        assert(expectedOutput == actualOutput)
    }
}