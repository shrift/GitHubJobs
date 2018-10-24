package com.bubbletastic.githubjobs

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.bubbletastic.githubjobs.service.JobsService
import kotlin.math.roundToInt

fun main(_args: Array<String>) {
    GitHubJobs().findPredefinedJobs()
}

class GitHubJobs(private val jobsService: JobsService = JobsService()) {
    private val cities = listOf("Boston", "San Francisco", "Los Angeles", "Denver", "Boulder", "Chicago", "New York").sorted()
    private val languages = listOf("Ruby", "Go", "Python", "Java", "Kotlin", "Elixir", "PHP", "Swift", "Objective-C", "JavaScript", "Node").sorted()

    fun findPredefinedJobs() {
        val totalJobs = JsonArray<JsonObject>()
        cities.forEach { city ->
            println("$city:")
            languages.forEach { language ->
                val positions = jobsService.positionsByLocationAndDescription(city, language)
                totalJobs.addAll(positions)
                if (positions.size > 0) {
                    println("  - $language:")
                    val positionsString = buildPositionsString(positions)
                    print(positionsString)
                }
            }
        }
        println("Sourced: ${totalJobs.distinctBy { it.string("id") }.count()} job postings")
    }

    internal fun buildPositionsString(positions: JsonArray<JsonObject>): StringBuilder {
        val positionsBuilder = StringBuilder()
        positions.groupingBy { it.string("type") }
                .eachCount()
                .forEach {
                    val typePercent = (it.value.toFloat() / positions.size.toFloat()) * 100f
                    (positionsBuilder.append("    - ${it.key}: ${typePercent.roundToInt()}%\n"))
                }
        return positionsBuilder;
    }
}
