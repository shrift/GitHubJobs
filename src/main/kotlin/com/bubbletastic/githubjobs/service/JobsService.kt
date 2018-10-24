package com.bubbletastic.githubjobs.service

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import khttp.get
import java.net.URLEncoder

@Suppress("UNCHECKED_CAST")
class JobsService {

    private val pageSize = 50

    fun positionsByLocationAndDescription(location: String, description: String): JsonArray<JsonObject> {
        val encodedLocation = URLEncoder.encode(location, "utf-8")
        val encodedDescription = URLEncoder.encode(description, "utf-8")
        val postings = accumulatePages("https://jobs.github.com/positions.json?location=$encodedLocation&description=$encodedDescription")
        return postings
    }

    private fun accumulatePages(queryUrl: String): JsonArray<JsonObject> {
        var morePages = true
        var pageNum = 0
        val pages = JsonArray<JsonObject>()
        while (morePages) {
            val result = get("$queryUrl&page=$pageNum", stream = true)
            val page = Parser().parse(result.raw) as JsonArray<JsonObject>
            pages.addAll(page)
            if (page.size < pageSize) {
                //This size is an assumption. The jobs api says they default to 50, but offer a page size query param.
                //I'm taking them at their word rather than making requests until I get 0 back (the last one would be a waste of network usage).
                morePages = false
            } else {
                pageNum++
            }
        }
        return pages
    }

}